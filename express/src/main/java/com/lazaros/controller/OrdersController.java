package com.lazaros.controller;

import com.google.gson.Gson;
import com.lazaros.beans.OrdersBeans;
import com.lazaros.beans.CustomerBeans;
import com.lazaros.dao.OrdersDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.Collections;

@WebServlet("/OrdersController")
public class OrdersController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrdersDAO orderDAO;

    public OrdersController() {
        super();
        orderDAO = new OrdersDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "LISTORDERS":
                listOrdersAPI(request, response);
                break;
            case "COMPLETE":
                completeOrder(request, response);
                break;
            default:
                listOrdersAPI(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "ADD":
                addOrderItem(request, response);
                break;
            case "DELETE":
                deleteOrderItem(request, response);
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
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

    private void listOrdersAPI(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        List<OrdersBeans> orders = null;
        if (session != null && session.getAttribute("loggedInCustomer") != null) {
            CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
            int customerId = loggedInCustomer.getCustomer_id();
            orders = orderDAO.getOrdersByCustomerId(customerId);
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(orders));
    }

    private void addOrderItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        int productId = Integer.parseInt(request.getParameter("id"));
        CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
        int customerId = loggedInCustomer.getCustomer_id();

        OrdersBeans orderItem = orderDAO.getOrderItem(customerId, productId);
        boolean success;
        if (orderItem == null) {
            orderItem = new OrdersBeans(0, new Date(System.currentTimeMillis()), true, customerId, 0.0);
            success = orderDAO.createOrder(orderItem) > 0;
        } else {
            success = orderDAO.increaseQuantity(productId, customerId);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void deleteOrderItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        boolean success = orderDAO.deleteOrderItem(orderId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void increaseQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        int productId = Integer.parseInt(request.getParameter("id"));
        CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
        int customerId = loggedInCustomer.getCustomer_id();

        boolean success = orderDAO.increaseQuantity(productId, customerId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void decreaseQuantity(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        int productId = Integer.parseInt(request.getParameter("id"));
        CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
        int customerId = loggedInCustomer.getCustomer_id();

        boolean success = orderDAO.decreaseQuantity(productId, customerId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", success)));
    }

    private void completeOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Satın alım sayfasına yönlendiriliyorsunuz!');");
        out.println(
                "window.location.href='" + request.getContextPath() + "/views/orderPage.jsp';");
        out.println("</script>");
    }
}
