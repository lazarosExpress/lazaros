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
    <div class="product-details" >
        <h1 class="product-title"><%= product.getProduct_name() %></h1>
        <p class="product-category">Kategori: <a href="javascript:void(0)" onclick="getCategoryProductList(<%=product.getCategory_id()%>)"><span class="category-name"><%= product.getCategory_name() %></span></a></p>
        <p class="product-category">Satıcı: <a href="javascript:void(1)" onclick="getSupplierProductList(<%=product.getSupplier_id()%>)"><span class="shop-name"><%= product.getSupplier_shopName() %></span></a></p>
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
            Ürünün Fiyatı: <span class="original-price"><%= product.getProduct_prize() %>₺</span>
            Ürünün Fiyatı: <span class="price"><%= product.getProduct_prize() %>₺</span>
        </p>
        <a href="javascript:void(0);" onclick="addToCart(<%= product.getProduct_id() %>);" class="store-link">Add to Cart</a>
    </div>
</div>
<%
}
%>
<div id="message-box" style="display: none; padding: 10px; background-color: #dff0d8; color: #3c763d; border: 1px solid #d6e9c6; border-radius: 4px; margin-top: 10px;"></div>

<script>
    function addToCart(productId) {
        fetch('<%= request.getContextPath() %>/BasketController?action=ADD&id=' + productId, {
            method: 'POST'
        })
        .then(response => response.json())
        .then(data => {
            const messageBox = document.getElementById('message-box');
            if (data.success) {
                messageBox.textContent = 'Ürün sepete eklendi.';
                messageBox.style.backgroundColor = '#dff0d8';
                messageBox.style.color = '#3c763d';
            } else {
                messageBox.textContent = 'Ürün sepete eklenirken bir hata oluştu.';
                messageBox.style.backgroundColor = '#f2dede';
                messageBox.style.color = '#a94442';
            }
            messageBox.style.display = 'block';
            setTimeout(() => {
                messageBox.style.display = 'none';
            }, 3000); // 3 saniye sonra mesajı gizler
        })
        .catch(error => {
            console.error('Error adding to cart:', error);
            const messageBox = document.getElementById('message-box');
            messageBox.textContent = 'Ürün sepete eklenirken bir hata oluştu.';
            messageBox.style.backgroundColor = '#f2dede';
            messageBox.style.color = '#a94442';
            messageBox.style.display = 'block';
            setTimeout(() => {
                messageBox.style.display = 'none';
            }, 3000);
        });
    }
    function getCategoryProductList (categoryId) {
        window.location.href = `<%= request.getContextPath() %>/views/categoryProductList.jsp?categoryId=` + categoryId;
    }
    function getSupplierProductList(supplierId) {
        window.location.href = '<%= request.getContextPath() %>/views/suppliersProductPage.jsp?supplierId=' + supplierId;
    }
</script>
