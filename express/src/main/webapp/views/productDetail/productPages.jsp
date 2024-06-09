<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.lazaros.beans.ProductBeans" %>
<%@ include file="../../components/header/header.jsp" %>
<%@ include file="../../components/navbar/navbar.jsp" %>
<%@ include file="../../components/footer/footer.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productPageStyle.css">

<script src="${pageContext.request.contextPath}/js/productPageScript.js"></script>

<%
ProductBeans product = (ProductBeans) request.getAttribute("product");
if (product == null) {
%>
    <p>Ürün bulunamadı.</p>
<%
} else {
%>
<div class="container">
    <div class="product-image">
        <img id="mainImage" src="<%= request.getContextPath() %>/productImg/<%= product.getProduct_imgUrl() %>" alt="<%= product.getProduct_name() %>">
    </div>
    <div class="product-details">
        <h1 class="product-title"><%= product.getProduct_name() %></h1>
        <p class="product-category">Kategori: <span class="category-name"><%= product.getCategory_name() %></span></p>
        <p class="product-category">Satıcı: <span class="shop-name"><%= product.getSupplier_shopName() %></span></p>
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Description')" id="defaultOpen">Ürün Açıklaması</button>
            <button class="tablinks" onclick="openTab(event, 'Specifications')">Ürün Özellikleri</button>
        </div>
        <div id="Description" class="tabcontent">
            <p><%= product.getProduct_explanation() %></p>
        </div>
        <div id="Specifications" class="tabcontent">
            <ul>
                <li><%= product.getProduct_properties() %></li>
            </ul>
        </div>
        <p class="product-price">
            Ürünün Fiyatı: <span class="original-price">$<%= product.getProduct_prize() %></span>
            Ürünün Fiyatı: <span class="price">$<%= product.getProduct_prize() %></span>
        </p>
        <a href="<%= request.getContextPath() %>/BasketController?action=ADD&id=<%= product.getProduct_id() %>" class="store-link">Add to Cart</a>
    </div>
</div>
<%
}
%>