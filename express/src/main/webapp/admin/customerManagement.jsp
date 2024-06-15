<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="tr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customerManagementStyle.css" />
    <script src="${pageContext.request.contextPath}/js/customerManagementScript.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" />

</head>

<body>
    <%@ include file="../components/adminHeader/adminHeader.jsp" %>
    <button class="add-customer-btn" onclick="location.href='${pageContext.request.contextPath}/admin/addCustomerManagement.jsp';">Müşteri Ekle</button>
    <section class="container">
        <h2 class="page-title">Müşteri Yönetimi</h2>
        <div class="card container">
            <h3>Müşteriler Listesi</h3>
            <table class="customer-table">
                <thead>
                    <tr id="customer-tabel">
                        <th>Müşteri Adı</th>
                        <th>Müşteri Soyadı</th>
                        <th>E-posta</th>
                        <th>Telefon Numarası</th>
                        <th>Düzenle</th>
                    </tr>
                </thead>
                <tbody id="customerList">
                    <!-- Müşteri bilgileri buraya eklenecek -->
                </tbody>
            </table>
        </div>
    </section>    
    <%@ include file="../components/footer/footer.jsp" %>
</body>

</html>
