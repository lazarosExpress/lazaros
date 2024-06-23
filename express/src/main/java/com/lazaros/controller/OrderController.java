package com.lazaros.controller;

import com.google.gson.Gson;
import com.lazaros.beans.CustomerBeans;
import com.lazaros.beans.OrdersBeans;
import com.lazaros.beans.SupplierBeans;
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
        System.out.println("get " + action);
        switch (action) {
            case "LISTORDERS":
                listOrders(request, response);
                break;
            case "LISTCUSTOMERORDERS":
                listCustomerOrders(request, response);
                break;
            case "LISTSUPPLIERORDERS":
                getOrdersBySupplierId(request, response);
                break;
            case "ORDERDETAILS":
                getOrderDetails(request, response);
                break;
            case "ORDERMANAGEMENTDETAILS":
                getOrderManagementDetails(request, response);
                break;
            case "CHANGEORDERSTATUS":
                changeOrderStatus(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("post " + action);
        switch (action) {
            case "CHANGEORDERSTATUS":
                changeOrderStatus(request, response);
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
            response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
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

    private void getOrdersBySupplierId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInSupplier") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        SupplierBeans loggedInSupplier = (SupplierBeans) session.getAttribute("loggedInSupplier");
        if (loggedInSupplier == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(new Gson().toJson(Collections.singletonMap("success", false)));
            return;
        }

        int supplierId = loggedInSupplier.getSupplier_id();
        System.out.println("supplierID: " + supplierId);

        List<OrdersBeans> supplierOrders = orderDAO.getOrdersBySupplierId(supplierId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(supplierOrders));
        out.flush();
    }

    @SuppressWarnings("unused")
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

    private void getOrderManagementDetails(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));

        List<OrdersBeans> orderDetails = orderDAO.getOrderManagementDetailsById(orderId, supplierId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.write(new Gson().toJson(orderDetails));
        out.flush();
    }

    private void changeOrderStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        boolean newStatus = Boolean.parseBoolean(request.getParameter("newStatus"));
        orderDAO.changeOrderStatus(orderId, newStatus);
        response.getWriter().write("Success");
    }
}
