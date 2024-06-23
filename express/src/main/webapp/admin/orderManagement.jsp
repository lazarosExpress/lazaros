<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sipariş Yönetimi</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderManagementStyle.css" />
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="${pageContext.request.contextPath}/js/orderManagementScript.js"></script>
</head>
<body>
    <%@ include file="../components/adminHeader/adminHeader.jsp" %>
    <section class="container">
        <div class="section">
            <h2>Siparişler Yönetimi</h2>
            <div class="card">
                <h3>Devam Eden Siparişler</h3>
                <div class="table-container">
                    <table id="ongoingOrdersTable">
                        <thead>
                            <tr>
                                <th>Ürün</th>
                                <th>Müşteri</th>
                                <th>Detay/Durumu</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Dynamic ongoing orders -->
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card">
                <h3>Tamamlanmış Siparişler</h3>
                <div class="table-container">
                    <table id="completedOrdersTable">
                        <thead>
                            <tr>
                                <th>Ürün</th>
                                <th>Müşteri</th>
                                <th>Detay/Durum</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Dynamic completed orders -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </section>
    <%@ include file="../components/footer/footer.jsp" %>
</body>
</html>
