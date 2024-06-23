package com.lazaros.controller;

import com.google.gson.Gson;
import com.lazaros.beans.SupplierBeans;
import com.lazaros.dao.SupplierDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

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
            case "FETCH_USER_INFO":
                fetchUserInfo(request, response);
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

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInSupplier") == null) {
            if (action == null || !(action.equals("LOGIN") || action.equals("CREATE"))) {
                response.sendRedirect(request.getContextPath() + "/views/login/login.jsp");
                return;
            }
        }

        switch (action) {
            case "CREATE":
                createSupplier(request, response);
                break;
            case "UPDATE":
                updateSupplier(request, response);
                break;
            case "LOGIN":
                loginSupplier(request, response);
                break;
            case "UPDATE_USER_INFO":
                updateUserInfo(request, response);
                break;
            case "UPDATE_PASSWORD":
                updatePassword(request, response);
                break;
            case "DELETE_ACCOUNT":
                deleteAccount(request, response);
                break;
            default:
                listSuppliers(request, response);
                break;
        }
    }

    private void listSuppliers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SupplierBeans> supplierList = SupplierDAO.getAllSuppliers();
        request.setAttribute("supplierList", supplierList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/supplierList.jsp");
        dispatcher.forward(request, response);
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

        SupplierBeans newSupplier = new SupplierBeans(0, firstName, lastName, shopName, iban, eMail, password, number);
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
                Cookie emailCookie = new Cookie("supplier_eMail", eMail);
                emailCookie.setPath("/");
                emailCookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(emailCookie);

                Cookie idCookie = new Cookie("supplier_id", String.valueOf(supplier.getSupplier_id()));
                idCookie.setPath("/");
                idCookie.setMaxAge(7 * 24 * 60 * 60);
                response.addCookie(idCookie);
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

        String[] cookieNames = { "supplier_eMail", "supplier_id" };
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                for (String cookieName : cookieNames) {
                    if (cookie.getName().equals(cookieName)) {
                        cookie.setValue(null);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
        }

        response.sendRedirect(request.getContextPath());
    }

    private void fetchUserInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        SupplierBeans loggedInSupplier = (SupplierBeans) session.getAttribute("loggedInSupplier");
        if (loggedInSupplier != null) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            String json = new Gson().toJson(loggedInSupplier);
            out.write(json);
            out.flush();
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private void updateUserInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        SupplierBeans loggedInSupplier = (SupplierBeans) session.getAttribute("loggedInSupplier");

        if (loggedInSupplier == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        SupplierBeans updatedSupplier = gson.fromJson(reader, SupplierBeans.class);

        loggedInSupplier.setSupplier_firstName(updatedSupplier.getSupplier_firstName());
        loggedInSupplier.setSupplier_lastName(updatedSupplier.getSupplier_lastName());
        loggedInSupplier.setSupplier_shopName(updatedSupplier.getSupplier_shopName());
        loggedInSupplier.setSupplier_iban(updatedSupplier.getSupplier_iban());
        loggedInSupplier.setSupplier_phoneNumber(updatedSupplier.getSupplier_phoneNumber());

        boolean isUpdated = supplierDAO.updateSupplier(loggedInSupplier);

        if (isUpdated) {
            response.getWriter().write("Bilgiler başarıyla güncellendi.");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Bilgiler güncellenemedi.");
        }
    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        SupplierBeans loggedInSupplier = (SupplierBeans) session.getAttribute("loggedInSupplier");

        if (loggedInSupplier == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        Map<String, String> passwordInfo = gson.fromJson(reader, Map.class);

        String currentPassword = passwordInfo.get("currentPassword");
        String newPassword = passwordInfo.get("newPassword");
        String confirmPassword = passwordInfo.get("confirmPassword");

        if (!loggedInSupplier.getSupplier_password().equals(currentPassword)) {
            response.getWriter().write("Şu anki şifre yanlış.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            response.getWriter().write("Yeni şifreler eşleşmiyor.");
            return;
        }
        int supplierId = loggedInSupplier.getSupplier_id();
        boolean isUpdated = supplierDAO.updateSupplierPassword(newPassword, supplierId);

        if (isUpdated) {
            response.getWriter().write("Şifre başarıyla güncellendi.");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Şifre güncellenemedi.");
        }
    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        Map<String, String> deleteInfo = gson.fromJson(reader, Map.class);

        String email = deleteInfo.get("email");
        String password = deleteInfo.get("password");

        SupplierBeans supplier = supplierDAO.getSupplierByEmail(email);

        if (supplier != null && supplier.getSupplier_password().equals(password)) {
            supplierDAO.deleteSupplier(supplier.getSupplier_id());

            // Oturumu sonlandır
            logoutSupplierDeleteAccount(request, response);

            response.getWriter().write("Hesap başarıyla silindi.");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "E-posta veya şifre yanlış.");
        }
    }
    private void logoutSupplierDeleteAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Oturumu sonlandır
        HttpSession session = request.getSession(false); // mevcut oturumu al
        if (session != null) {
            session.invalidate(); // oturumu geçersiz kıl
        }
        response.getWriter().write("Çıkış yapıldı.");
    }
}
