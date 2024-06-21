package com.lazaros.controller;

import com.google.gson.Gson;
import com.lazaros.beans.OrdersBeans;
import com.lazaros.beans.CustomerBeans;
import com.lazaros.dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet("/OrderController")
public class OrderController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderDAO orderDAO;

    public OrderController() {
        super();
        orderDAO = new OrderDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "LISTORDERS":
                listOrders(request, response);
                break;
            case "LISTCUSTOMERORDERS":
                listCustomerOrders(request, response);
                break;
            case "ORDERDETAILS":
                getOrderDetails(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

    private void listOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<OrdersBeans> ongoingOrders = orderDAO.getOngoingOrders();
        List<OrdersBeans> completedOrders = orderDAO.getCompletedOrders();

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(new OrderResponse(ongoingOrders, completedOrders)));
        out.flush();
    }

    private void listCustomerOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInCustomer") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        CustomerBeans loggedInCustomer = (CustomerBeans) session.getAttribute("loggedInCustomer");
        int customerId = loggedInCustomer.getCustomer_id();

        List<OrdersBeans> customerOrders = orderDAO.getOrdersByCustomerId(customerId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(customerOrders));
        out.flush();
    }

    private static class OrderResponse {
        private final List<OrdersBeans> ongoing;
        private final List<OrdersBeans> completed;

        public OrderResponse(List<OrdersBeans> ongoing, List<OrdersBeans> completed) {
            this.ongoing = ongoing;
            this.completed = completed;
        }
    }

    private void getOrderDetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        List<OrdersBeans> orderDetails = orderDAO.getOrderDetailsById(orderId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(orderDetails));
        out.flush();
    }
}
