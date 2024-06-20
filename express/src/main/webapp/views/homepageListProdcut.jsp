<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepageListProdcutStyle.css">
<%@ include file="../attributes/homepageListProdcutAttributes.jsp" %>

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
</script>
