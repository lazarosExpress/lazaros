package com.lazaros.controller;

import com.lazaros.beans.SupplierBeans;
import com.lazaros.dao.SupplierDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SupplierController")
public class SupplierController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SupplierDAO supplierDAO;

    public SupplierController() {
        supplierDAO = new SupplierDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String action = request.getParameter("action");

        // Debug çıktısı
        System.out.println("Action parameter in doGet: " + action);

        // Redirect to login if the session is invalid and the action is not LOGIN or
        // CREATE
        if (session == null || session.getAttribute("loggedInSupplier") == null) {
            if (action == null || !(action.equals("LOGIN") || action.equals("CREATE"))) {
                response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
                return;
            }
        }

        if (action == null) {
            action = "LIST";
        }

        switch (action) {
            case "LIST":
                listSuppliers(request, response);
                break;
            case "CREATE":
                createSupplier(request, response);
                break;
            case "EDIT":
                showEditForm(request, response);
                break;
            case "DELETE":
                deleteSupplier(request, response);
                break;
            case "LOGIN":
                loginSupplier(request, response);
                break;
            case "LOGOUT":
                logoutSupplier(request, response);
                break;
            default:
                listSuppliers(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        // Debug çıktısı
        System.out.println("Action parameter in doPost: " + action);

        HttpSession session = request.getSession(false);

        // Redirect to login if the session is invalid and the action is not LOGIN or
        // CREATE
        if (session == null || session.getAttribute("loggedInSupplier") == null) {
            if (action == null || !(action.equals("LOGIN") || action.equals("CREATE"))) {
                response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
                return;
            }
        }

        if ("CREATE".equals(action)) {
            createSupplier(request, response);
        } else if ("UPDATE".equals(action)) {
            updateSupplier(request, response);
        } else if ("LOGIN".equals(action)) {
            loginSupplier(request, response);
        }
    }

    private void listSuppliers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SupplierBeans> supplierList = SupplierDAO.getAllSuppliers();
        request.setAttribute("supplierList", supplierList);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        SupplierBeans existingSupplier = supplierDAO.getSupplierById(id);
        request.setAttribute("supplier", existingSupplier);
        RequestDispatcher dispatcher = request.getRequestDispatcher("supplier-form.jsp");
        dispatcher.forward(request, response);
    }

    private void createSupplier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("supplier_firstName");
        String lastName = request.getParameter("supplier_lastName");
        String shopName = request.getParameter("supplier_shopName");
        String eMail = request.getParameter("supplier_eMail");
        String password = request.getParameter("supplier_password");
        String iban = request.getParameter("supplier_iban");
        String number = request.getParameter("supplier_phoneNumber");

        SupplierBeans existingSupplier = supplierDAO.getSupplierByEmail(eMail);
        if (existingSupplier != null) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Bu e-posta adresi zaten kayıtlı!');");
            out.println(
                    "window.location.href='" + request.getContextPath() + "/supplierLogin/supplierRegistration.jsp';");
            out.println("</script>");
            return;
        }

        SupplierBeans newSupplier = new SupplierBeans(firstName, lastName, shopName, iban, eMail, password, number);
        supplierDAO.insertSupplier(newSupplier);

        response.sendRedirect(request.getContextPath() + "/views/supplierLogin/supplierLogin.jsp");
    }

    private void updateSupplier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("supplier_id"));
        String firstName = request.getParameter("supplier_firstName");
        String lastName = request.getParameter("supplier_lastName");
        String shopName = request.getParameter("supplier_shopName");
        String iban = request.getParameter("supplier_iban");
        String eMail = request.getParameter("supplier_eMail");
        String password = request.getParameter("supplier_password");
        String phoneNumber = request.getParameter("supplier_phoneNumber");

        SupplierBeans supplierToUpdate = new SupplierBeans(id, firstName, lastName, shopName, iban, eMail, password,
                phoneNumber);
        supplierDAO.updateSupplier(supplierToUpdate);

        response.sendRedirect("SupplierController?action=LIST");
    }

    private void deleteSupplier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        supplierDAO.deleteSupplier(id);
        response.sendRedirect("SupplierController?action=LIST");
    }

    private void loginSupplier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String eMail = request.getParameter("supplier_eMail");
        String password = request.getParameter("supplier_password");

        boolean loginSuccessful = supplierDAO.loginSupplier(eMail, password);

        if (loginSuccessful) {
            HttpSession session = request.getSession();
            SupplierBeans supplier = supplierDAO.getSupplierByEmail(eMail);

            session.setAttribute("loggedInSupplier", supplier);
            response.sendRedirect(request.getContextPath() + "/admin/supplierAdminPanel.jsp");

            boolean rememberMe = request.getParameter("rememberMe") != null;
            request.getSession().setAttribute("supplier_eMail", eMail);

            if (rememberMe) {
                Cookie cookie = new Cookie("supplier_eMail", eMail);
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

    private void logoutSupplier(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        String cookieName = "supplier_eMail";
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
}
