<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sipariş Detayları</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderDetailStyle.css">
</head>
<%@ include file="../components/header/header.jsp" %>
<%@ include file="../components/footer/footer.jsp" %>
<body>
    <div class="order-details-container">
        <h1>Sipariş Detayım</h1>
        <div id="order-summary"></div>
    </div>

    <script src="${pageContext.request.contextPath}/js/orderDetailScript.js"></script>
</body>
</html>
