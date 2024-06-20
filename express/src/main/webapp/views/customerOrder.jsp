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
<body>
    <div class="header-card">
        <h1>Siparişlerim</h1>
        <input type="text" id="search-bar" placeholder="Ürün ismi veya Marka ara">
        <div class="filters">
            <button class="filter-btn" onclick="filterOrders('all')">Tümü</button>
            <button class="filter-btn" onclick="filterOrders('delivered')">Teslim Edilen</button>
            <button class="filter-btn" onclick="filterOrders('ongoing')">Devam Eden</button>
            <button class="filter-btn" onclick="filterOrders('returned')">İade Edilen</button>
            <button class="filter-btn" onclick="filterOrders('cancelled')">İptal Olan</button>
        </div>
    </div>
    <div class="orders-container">
        <!-- Dynamic orders will be loaded here -->
    </div>
</body>
</html>
