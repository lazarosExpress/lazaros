<%@page import="java.util.*"%>
<%@ page import="com.lazaros.dao.ProductDAO" %>
<%@ page import="com.lazaros.beans.ProductBeans" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
ProductBeans auth = (ProductBeans) request.getSession().getAttribute("auth");
if(auth != null){
    request.setAttribute("auth", auth);
}

ProductDAO productDAO = new ProductDAO();
List<ProductBeans> products = productDAO.getFullProduct();
%>
