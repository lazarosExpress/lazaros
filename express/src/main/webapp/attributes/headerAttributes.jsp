<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="jakarta.servlet.http.Cookie"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<%@ page import="java.util.List"%>
<%@ page import="com.lazaros.beans.CustomerBeans"%>
<%@ page import="com.lazaros.beans.BasketBeans"%>
<%@ page import="com.lazaros.dao.CustomerDAO"%>
<%@ page import="com.lazaros.dao.BasketDAO"%>
<%@ page import="com.lazaros.beans.ProductBeans"%>
<%@ page import="com.lazaros.dao.ProductDAO"%>
<%
    HttpSession httpSession = request.getSession(false);
    String customer_eMail = null;
    int customerId = -1;
    List<BasketBeans> basket = null;

    if (httpSession != null && httpSession.getAttribute("loggedInCustomer") != null) {
        CustomerBeans customer = (CustomerBeans) httpSession.getAttribute("loggedInCustomer");
        customer_eMail = customer.getCustomer_eMail();
        customerId = customer.getCustomer_id();
        BasketDAO basketDAO = new BasketDAO();
        basket = basketDAO.getBasketByCustomerId(customerId);
    } else {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("customer_eMail")) {
                    String email = cookie.getValue();
                    CustomerDAO customerDAO = new CustomerDAO();
                    CustomerBeans customer = customerDAO.getCustomerByEmail(email);
                    if (customer != null) {
                        customer_eMail = customer.getCustomer_eMail();
                        customerId = customer.getCustomer_id();
                        if (httpSession == null) {
                            httpSession = request.getSession(true);
                        }
                        httpSession.setAttribute("loggedInCustomer", customer);
                        BasketDAO basketDAO = new BasketDAO();
                        basket = basketDAO.getBasketByCustomerId(customerId);
                    }
                    break;
                }
            }
        }
    }
%>
<script type="text/javascript">
    const customerId = <%= customerId %>;
</script>