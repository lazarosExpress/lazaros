<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../attributes/headerAttributes.jsp"%>
<%@ include file="../components/footer/footer.jsp" %>

<div class="basket">
    <h2>Sepetiniz</h2>
    <c:choose>
        <c:when test="${not empty basket}">
            <c:forEach var="item" items="${basket}" varStatus="status">
                <c:set var="product" value="${products[status.index]}"/>
                <div class="basket-item">
                    <img src="${pageContext.request.contextPath}/productImg/${product.product_imgUrl}" alt="${product.product_name}">
                    <div class="basket-item-info">
                        <h3>${product.product_name}</h3>
                        <p>Adet: <span class="item-qty">${item.basket_qty}</span></p>
                        <p class="price">${product.product_price} TL</p>
                        <button class="qty-btn decrease-qty" data-id="${item.product_id}">-</button>
                        <button class="qty-btn increase-qty" data-id="${item.product_id}">+</button>
                    </div>
                    <a href="BasketController?action=DELETE&id=${item.basket_id}" class="remove-btn">Kaldır</a>
                </div>
            </c:forEach>
            <a href="BasketController?action=COMPLETE" class="complete-order-btn">Siparişi Tamamla</a>
        </c:when>
        <c:otherwise>
            <p>Sepetiniz boş.</p>
        </c:otherwise>
    </c:choose>
</div>
