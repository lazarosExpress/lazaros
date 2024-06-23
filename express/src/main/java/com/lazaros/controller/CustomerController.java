package com.lazaros.controller;

import com.google.gson.Gson;
import com.lazaros.beans.CustomerBeans;
import com.lazaros.dao.CustomerDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CustomerController.class.getName());
    private static final long serialVersionUID = 1L;
    private final CustomerDAO customerDAO;

    public CustomerController() {
        super();
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "LIST";
        }

        switch (action) {
            case "LIST":
                listCustomers(request, response);
                break;
            case "LIST_JSON":
                listCustomersJson(request, response);
                break;
            case "EDIT":
                showEditForm(request, response);
                break;
            case "DELETE":
                deleteCustomer(request, response);
                break;
            case "UPDATEDETAILSWITHSUPPLIER":
                updateDetailCustomerWithSupplier(request, response);
                break;
            case "LOGIN":
                loginCustomer(request, response);
                break;
            case "CREATE":
                createCustomer(request, response);
                break;
            case "LOGOUT":
                logoutCustomer(request, response);
                break;
            default:
                listCustomers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "ADD":
                    createCustomer(request, response);
                    break;
                case "DELETE":
                    deleteCustomer(request, response);
                    break;
                case "CREATE":
                    createCustomer(request, response);
                    break;
                case "CREATEWITHSUPPLIER":
                    createCustomerWithSupplier(request, response);
                    break;
                case "LOGIN":
                    loginCustomer(request, response);
                    break;
                case "UPDATE":
                    updateCustomer(request, response);
                    break;
                case "UPDATESUPPLIER":
                    updateCustomerWithSupplier(request, response);
                    break;
                default:
                    listCustomers(request, response);
                    break;
            }
        } else {
            listCustomers(request, response);
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CustomerBeans> customerList = customerDAO.getAllCustomers();
        request.setAttribute("customerList", customerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/customerManagement.jsp");
        dispatcher.forward(request, response);
    }

    private void listCustomersJson(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInSupplier") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
            return;
        }

        try {
            List<CustomerBeans> customers = customerDAO.getAllCustomers();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(customers);
            out.write(json);
            out.flush();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error listing customers", e);
            throw new ServletException("Error listing customers", e);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CustomerBeans existingCustomer = customerDAO.getCustomerById(id);
        request.setAttribute("customer", existingCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer-form.jsp");
        dispatcher.forward(request, response);
    }

    private void createCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eMail = request.getParameter("customer_eMail");
        String firstName = request.getParameter("customer_firstName");
        String lastName = request.getParameter("customer_lastName");
        String password = request.getParameter("customer_password");
        String number = request.getParameter("customer_number");

        CustomerBeans existingCustomer = customerDAO.getCustomerByEmail(eMail);
        if (existingCustomer != null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Bu e-posta adresi zaten kayıtlı!');");
            out.println("window.location.href='" + request.getContextPath() + "/login/registration.jsp';");
            out.println("</script>");
            return;
        }

        CustomerBeans newCustomer = new CustomerBeans(0, eMail, firstName, lastName, password, number);
        customerDAO.insertCustomer(newCustomer);

        response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
    }

    private void createCustomerWithSupplier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eMail = request.getParameter("customer_eMail");
        String firstName = request.getParameter("customer_firstName");
        String lastName = request.getParameter("customer_lastName");
        String password = request.getParameter("customer_password");
        String number = request.getParameter("customer_number");

        CustomerBeans existingCustomer = customerDAO.getCustomerByEmail(eMail);
        if (existingCustomer != null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Bu e-posta adresi zaten kayıtlı!');");
            out.println("window.location.href='" + request.getContextPath() + "/admin/customerManagement.jsp';");
            out.println("</script>");
            return;
        }

        CustomerBeans newCustomer = new CustomerBeans(0, eMail, firstName, lastName, password, number);
        customerDAO.insertCustomer(newCustomer);

        response.sendRedirect(request.getContextPath() + "/admin/customerManagement.jsp");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("customer_id"));
        String eMail = request.getParameter("customer_eMail");
        String firstName = request.getParameter("customer_firstName");
        String lastName = request.getParameter("customer_lastName");
        String password = request.getParameter("customer_password");
        String number = request.getParameter("customer_number");

        CustomerBeans customerToUpdate = new CustomerBeans(id, eMail, firstName, lastName, password, number);
        customerDAO.updateCustomer(customerToUpdate);

        response.sendRedirect("CustomerController?action=LIST");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerDAO.deleteCustomer(id);
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect(request.getContextPath() + "/admin/productManagement.jsp");
    }

    private void loginCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eMail = request.getParameter("customer_eMail");
        String password = request.getParameter("customer_password");

        boolean loginSuccessful = customerDAO.loginCustomer(eMail, password);

        if (loginSuccessful) {
            HttpSession session = request.getSession();
            CustomerBeans customer = customerDAO.getCustomerByEmail(eMail);

            session.setAttribute("loggedInCustomer", customer);
            response.sendRedirect(request.getContextPath());

            boolean rememberMe = request.getParameter("rememberMe") != null;
            request.getSession().setAttribute("customer_eMail", eMail);

            if (rememberMe) {
                Cookie cookie = new Cookie("customer_eMail", eMail);
                cookie.setPath("/");
                cookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(cookie);
            }

        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Hatalı giriş yaptınız!');");
            out.println("window.location.href='" + request.getContextPath() + "/views/login/login.jsp';");
            out.println("</script>");
        }
    }

    private void logoutCustomer(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        String cookieName = "customer_eMail";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        response.sendRedirect(request.getContextPath());
    }

    private void updateDetailCustomerWithSupplier(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int customerId;
        try {
            customerId = Integer.parseInt(request.getParameter("customerId"));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Geçersiz müşteri ID formatı.");
            return;
        }

        CustomerBeans customer = customerDAO.getCustomerByIdWithDetail(customerId);
        if (customer == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Müşteri bulunamadı.");
            return;
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String json = new Gson().toJson(customer);
        out.write(json);
        out.flush();
    }

    private void updateCustomerWithSupplier(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String customerIdParam = request.getParameter("customerId");
        if (customerIdParam == null || customerIdParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Geçersiz müşteri ID.");
            return;
        }

        int customerId;
        try {
            customerId = Integer.parseInt(customerIdParam);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Geçersiz müşteri ID formatı.");
            return;
        }

        // JSON verisini parse et
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        CustomerBeans customerData = gson.fromJson(reader, CustomerBeans.class);

        String firstName = customerData.getCustomer_firstName();
        String lastName = customerData.getCustomer_lastName();
        String email = customerData.getCustomer_eMail();
        String phoneNumber = customerData.getCustomer_phoneNumber();

        // Loglama eklendi
        LOGGER.info("Updating customer with ID: " + customerId);
        LOGGER.info("First Name: " + firstName);
        LOGGER.info("Last Name: " + lastName);
        LOGGER.info("Email: " + email);
        LOGGER.info("Phone Number: " + phoneNumber);

        CustomerBeans customerToUpdate = new CustomerBeans(customerId, email, firstName, lastName, null, phoneNumber);
        boolean isUpdated = customerDAO.updateCustomerWithSupplier(customerToUpdate);

        if (isUpdated) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Müşteri başarıyla güncellendi.");
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Müşteri güncellenemedi.");
        }
    }

}
