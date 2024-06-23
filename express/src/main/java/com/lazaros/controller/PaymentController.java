package com.lazaros.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.google.gson.Gson;
import com.lazaros.beans.PaymentBeans;
import com.lazaros.dao.PaymentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PaymentDAO paymentDAO;

    public PaymentController() {
        super();
        paymentDAO = new PaymentDAO(); // PaymentDAO nesnesini başlat
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        System.out.println("Payment Controller GET: " + action);

        switch (action) {
            case "list":
                listPayment(request, response);
                break;
            default:
                listPayment(request, response);
                break;
        }
    }

    private void listPayment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<PaymentBeans> listPayment = paymentDAO.getAllPayment();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(listPayment);
            out.write(json);
            out.flush();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading payment");
        }
    }
}
