<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Siparişlerim</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customerOrderStyle.css">
    <script src="${pageContext.request.contextPath}/js/customerOrderScript.js"></script>
</head>
<%@ include file="../components/header/header.jsp" %>
<%@ include file="../components/footer/footer.jsp" %>
<body>
<div class="customer-order-style-css">

    <div class="header-card">
        <h1>Siparişlerim</h1>
    </div>
    <div class="orders-container">
        <!-- Dynamic orders will be loaded here -->
    </div>
</div>
</body>
</html>
