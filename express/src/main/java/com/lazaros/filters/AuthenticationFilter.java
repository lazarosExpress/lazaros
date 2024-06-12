package com.lazaros.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({ "/admin/*", "/views/customerInformation.jsp", "/views/address.jsp", "/views/orders.jsp" })
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        String requestURI = httpRequest.getRequestURI();
        if (requestURI.startsWith(httpRequest.getContextPath() + "/admin/")) {
            // Admin authentication check
            if (session == null || session.getAttribute("loggedInSupplier") == null) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/login/login.jsp");
                return;
            }
        } else {
            // Customer authentication check
            if (session == null || session.getAttribute("loggedInCustomer") == null) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/login/login.jsp");
                return;
            }
        }

        // Proceed to the next filter or target resource
        chain.doFilter(request, response);
    }

    public void destroy() {
        // Cleanup code, if needed
    }
}
