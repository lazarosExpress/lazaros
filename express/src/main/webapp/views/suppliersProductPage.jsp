<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.*"%>
<%@ page import="com.lazaros.dao.ProductDAO" %>
<%@ page import="com.lazaros.beans.ProductBeans" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
int supplierId = Integer.parseInt(request.getParameter("supplierId"));
ProductDAO productDAO = new ProductDAO();
List<ProductBeans> products = productDAO.getSupplierProduct(supplierId);
String supplierShopName = products.isEmpty() ? "" : products.get(0).getSupplier_shopName();

%>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Store Page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/suppliersProductPageStyle.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepageListProdcutStyle.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


</head>
<%@ include file="../components/header/header.jsp" %>
<%@ include file="../components/navbar/navbar.jsp" %>
<%@ include file="../components/footer/footer.jsp" %>
<body>
    <div class="container">
        <div class="header">
            <div><%=supplierShopName%></div>
            
            <div class="search-bar">
                <input type="text" placeholder="Mağazada ara...">
            </div>
        </div>
        <div class="products-section">
            <h2>Ürünler</h2>
            <section class="section-products">
                <div class="container">
                    <div class="row" id="productList">
                        <% if(!products.isEmpty()) {
                            for(ProductBeans p : products) { %>
                            <div class="col-md-3 col-lg-3 col-xl-3">
                                <div class="single-product">
                                    <div class="part-1">
                                        <img src="<%= request.getContextPath() %>/productImg/<%= p.getProduct_imgUrl() %>" alt="<%= p.getProduct_name() %>">
                                        <ul>
                                            <li><a href="javascript:void(0);" onclick="addToCart(<%=p.getProduct_id()%>);" class="cart-btn"><i class="fas fa-shopping-cart"></i></a></li>
                                            <li><a href="#"><i class="fas fa-heart"></i></a></li>
                                            <li><a href="#"><i class="fas fa-plus"></i></a></li>
                                            <li><a href="<%= request.getContextPath() %>/ProductController?action=DETAILS&id=<%=p.getProduct_id()%>"><i class="fas fa-expand"></i></a></li>
                                        </ul>
                                    </div>
                                    <div class="part-2">
                                        <div class="product-name-title"> <%=p.getProduct_name() %> </div>
                                        <div class="product-category-title"><%=p.getCategory_name()%></div>
                                        <div class="price-container">
                                            <span class="product-old-price">$79.99</span>
                                            <span class="product-price">$<%= p.getProduct_prize() %></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <% }
                        } %>
                    </div>
                </div>
            </section>
        </div>
    </div>
</body>
</html>
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
</script>