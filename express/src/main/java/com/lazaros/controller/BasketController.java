package com.lazaros.controller;

import com.google.gson.Gson;
import com.lazaros.beans.BasketBeans;
import com.lazaros.beans.CustomerBeans;
import com.lazaros.dao.BasketDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Collections;

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
        String action = request.getParameter("action");
        System.out.println("basket get " + action);

        switch (action) {
            case "LISTBASKET":
                listBasketAPI(request, response);
                break;
            case "LIST":
                listBasket(request, response);
                break;
            case "COMPLETE":
                completeBasket(request, response);
                break;
            default:
                listBasketAPI(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("basket post " + action);
        switch (action) {
            case "LISTBASKET":
                listBasketAPI(request, response);
                break;
            case "LIST":
                listBasket(request, response);
                break;
            case "ADD":
                addBasketItem(request, response);
                break;
            case "DELETE":
                deleteBasketItem(request, response);
                break;
            case "UPDATEQUANTITY":
                updateBasketQuantity(request, response);
                break;
            case "COMPLETE":
                completeBasket(request, response);
                break;
            case "INCREASE":
                increaseQuantity(request, response);
                break;
            case "DECREASE":
                decreaseQuantity(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

    private void listBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String customerIdParam = request.getParameter("customerId");
        if (customerIdParam != null) {
            try {
                int customerId = Integer.parseInt(customerIdParam);
                List<BasketBeans> basketList = basketDAO.getBasketByCustomerId(customerId);

                Gson gson = new Gson();
                String json = gson.toJson(basketList);

                PrintWriter out = response.getWriter();
                out.print(json);
                out.flush();
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customer ID format");
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "An error occurred while retrieving the basket");
                e.printStackTrace();
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Customer ID is required");
        }
    }

    private void listBasketAPI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        List<BasketBeans> basket = null;
        if (session != null && session.getAttribute("loggedInCustomer") != null) {
            CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
            int customerId = loggedInCustomer.getCustomer_id();
            basket = basketDAO.getBasketByCustomerId(customerId);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            if (basket != null) {
                System.out.println("Basket data: " + new Gson().toJson(basket));
                out.write(new Gson().toJson(basket));
            } else {
                System.out.println("Basket not found for customer ID: "
                        + (session != null ? ((CustomerBeans) session.getAttribute("loggedInCustomer")).getCustomer_id()
                                : "null"));
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.write(new Gson().toJson(Collections.singletonMap("error", "Basket not found")));
            }
        } catch (Exception e) {
            System.err.println("Error in listBasketAPI: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try (PrintWriter out = response.getWriter()) {
                out.write(new Gson().toJson(Collections.singletonMap("error", "Internal Server Error")));
            }
        }
    }

    private void addBasketItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    private void deleteBasketItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int basketId = Integer.parseInt(request.getParameter("id"));
        boolean success = basketDAO.deleteBasketItem(basketId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void updateBasketQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }
        CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
        int customerId = loggedInCustomer.getCustomer_id();

        boolean success = basketDAO.updateQuantity(productId, customerId, quantity);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void completeBasket(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Satın alım sayfasına yönlendiriliyorsunuz!');");
        out.println("window.location.href='" + request.getContextPath() + "/views/orderPage.jsp';");
        out.println("</script>");
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
}
