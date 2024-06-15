<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/headerStyle.css">
<script src="${pageContext.request.contextPath}/js/headerScript.js"></script>
<script>
    const contextPath = '<%= request.getContextPath() %>';
</script>

<div class="header">
    <div class="logo" style="padding-left: 30px">
        <figure>
            <img src="${pageContext.request.contextPath}/images/lazaros.png" alt="LAZAROS" height="50">
        </figure>
    </div>
    <div class="search-container">
        <form action="#" class="search-form">
            <input type="search" placeholder="Ara" class="search-input" id="search-input">
            <button type="submit" class="search-button" id="search-button">Ara</button>
        </form>
    </div>
    <div class="icons">
        <c:choose>
            <c:when test="${not empty customer_eMail}">
                <a href="" class="icon-link" id="user-icon">
                    <i class="fas fa-user"></i>
                    <span>Hesabım</span>
                </a>
                <div class="user-menu" id="user-menu">
                    <div class="user-menu-header">
                        <span>Hoşgeldin, ${loggedInCustomer.customer_firstName}</span>
                        <button id="logout-button">Çıkış yap</button>
                    </div>
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/views/customerInformation.jsp">Profil Bilgilerim</a></li>
                        <li><a href="#list-item-2">Siparişlerim</a></li>
                        <li><a href="${pageContext.request.contextPath}/views/address.jsp">Adreslerim</a></li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <a href="views/login/login.jsp" class="icon-link">
                    <i class="fas fa-user"></i>
                    <span>Giriş Yap</span>
                </a>
            </c:otherwise>
        </c:choose>
        <a href="" class="icon-link" id="basket-icon">
            <i class="fas fa-shopping-cart"></i>
            <span>Sepet</span>
            <div class="dropdown-basket" id="dropdown-basket">
                <p>Sepetiniz boş.</p>
            </div>
        </a>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/basket.js"></script>
