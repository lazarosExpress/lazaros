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
                            <h3 class="product-name-title"><%=p.getProduct_name() %></h3>
                            <h3 class="product-category-title"><%=p.getCategory_name()%></h3>
                            <h4 class="product-old-price">$79.99</h4>
                            <h4 class="product-price">$<%=p.getProduct_prize()%></h4>
                        </div>
                    </div>
                </div>
                <% }
            } %>
        </div>
    </div>
</section>

<script>
    function addToCart(productId) {
        fetch('<%= request.getContextPath() %>/BasketController?action=ADD&id=' + productId, {
            method: 'GET'
        })
        .then(response => response.json())
        .then(data => {
            if (data.success) {} else {
                alert('Ürün sepete eklenirken bir hata oluştu.');
            }
        })
        .catch(error => console.error('Error adding to cart:', error));
    }
</script>
