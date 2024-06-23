<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>

<html>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/supplierAdminPanelStyle.css">

    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="${pageContext.request.contextPath}/js/supplierAdminPanelScript.js"></script>
</head>

<body>
    <%@ include file="../components/adminHeader/adminHeader.jsp" %>
    <div class="container">
        <div class="card" onclick="location.href='${pageContext.request.contextPath}/admin/productManagement.jsp';">
            <img src="${pageContext.request.contextPath}/images/productManagement.png" alt="Ürün Yönetimi">
            <p>Ürün Yönetimi</p>
        </div>
        <div class="card" onclick="location.href='${pageContext.request.contextPath}/admin/customerManagement.jsp';">
            <img src="${pageContext.request.contextPath}/images/customerManagement.png" alt="Müşteri Yönetimi">
            <p>Müşteri Yönetimi</p>
        </div>
        <div class="card" onclick="location.href='${pageContext.request.contextPath}/admin/orderManagement.jsp';">
            <img src="${pageContext.request.contextPath}/images/orderManagement.png" alt="Sipariş Yönetimi">
            <p>Sipariş Yönetimi</p>
        </div>
        <div class="card" onclick="location.href='${pageContext.request.contextPath}/admin/supplierInformationManagment.jsp';">
            <img src="${pageContext.request.contextPath}/images/storeManagement.png" alt="Mağaza Yönetimi">
            <p>Mağaza Yönetimi</p>
        </div>
    </div>
    <%@ include file="../components/footer/footer.jsp" %>
</body>

</html>