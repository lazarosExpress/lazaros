package com.lazaros.controller;

import com.google.gson.Gson;
import com.lazaros.beans.BasketBeans;
import com.lazaros.beans.CustomerBeans;
import com.lazaros.dao.BasketDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/BasketController")
public class BasketController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BasketDAO basketDAO;

    public BasketController() {
        super();
        basketDAO = new BasketDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "LIST";
        }

        switch (action) {
            case "LIST":
                listBasket(request, response);
                break;
            case "LISTBASKET":
                listBasketAPI(request, response);
                break;
            case "ADD":
                addBasketItem(request, response);
                break;
            case "DELETE":
                deleteBasketItem(request, response);
                break;
            case "INCREASE":
                increaseQuantity(request, response);
                break;
            case "DECREASE":
                decreaseQuantity(request, response);
                break;
            case "COMPLETE":
                completeOrder(request, response);
                break;
            default:
                listBasket(request, response);
                break;
        }
    }

    private void listBasket(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int customerId = -1;
        List<BasketBeans> basket = null;
        if (session != null && session.getAttribute("loggedInCustomer") != null) {
            CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
            customerId = loggedInCustomer.getCustomer_id();
            basket = basketDAO.getBasketByCustomerId(customerId);
            request.setAttribute("basket", basket);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/basket.jsp");
        dispatcher.forward(request, response);
    }

    private void listBasketAPI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        int customerId = -1;
        List<BasketBeans> basket = null;
        if (session != null && session.getAttribute("loggedInCustomer") != null) {
            CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
            customerId = loggedInCustomer.getCustomer_id();
            basket = basketDAO.getBasketByCustomerId(customerId);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(basket));
    }

    private void addBasketItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        int productId = Integer.parseInt(request.getParameter("id"));
        CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
        int customerId = loggedInCustomer.getCustomer_id();

        BasketBeans basketItem = basketDAO.getBasketItem(customerId, productId);

        boolean success;
        if (basketItem == null) {
            basketItem = new BasketBeans(0, 1, customerId, productId);
            success = basketDAO.addBasketItem(basketItem);
        } else {
            success = basketDAO.increaseQuantity(productId, customerId);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void deleteBasketItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int basketId = Integer.parseInt(request.getParameter("id"));
        boolean success = basketDAO.deleteBasketItem(basketId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void increaseQuantity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        int productId = Integer.parseInt(request.getParameter("id"));
        CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
        int customerId = loggedInCustomer.getCustomer_id();

        boolean success = basketDAO.increaseQuantity(productId, customerId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void decreaseQuantity(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        int productId = Integer.parseInt(request.getParameter("id"));
        CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
        int customerId = loggedInCustomer.getCustomer_id();

        boolean success = basketDAO.decreaseQuantity(productId, customerId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void completeOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Sipariş tamamlama işlemleri burada yapılacak
        response.sendRedirect("views/completeOrder.jsp");
    }
}
