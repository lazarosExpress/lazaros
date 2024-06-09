<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>

<html>

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/productManagementStyle.css" />
    
    <script src="${pageContext.request.contextPath}/js/productManagementScript.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>
    <header>
        <div><a href="${pageContext.request.contextPath}/admin/supplierAdminPanel.jsp" class="back-button"><i class="bi bi-arrow-left"></i></a></div>
        <img src="${pageContext.request.contextPath}/images/lazarosSupplier.png" alt="Logo">
        <div class="logout"><i id="logout-button" class="bi bi-box-arrow-right"></i></div>
    </header>
    <button class="add-product-btn" onclick="location.href='${pageContext.request.contextPath}/admin/addProductManagement.jsp';">Ürün Ekle</button>
    <section class="container">
        <h2 class="page-title">Ürün Yönetimi</h2>
        <div class="card container">
            <h3>Ürünler Listesi</h3>
            <table class="product-table">
                <thead>
                    <tr id="table-title">
                        <th>Ürün Adı</th>
                        <th>Fiyat</th>
                        <th>Stok Miktarı</th>
                        <th>Marka</th>
                        <th>Kategori</th>
                        <th>Ürün Açıklaması</th>
                        <th>Ürün Özellikleri</th>
                        <th>Ürünün Fotoğrafı</th>
                        <th>Düzenle</th>
                    </tr>
                </thead>
                <tbody id="productList">
                    <!-- Ürünler burada listelenecek -->
                </tbody>
            </table>
        </div>
    </section>
    <%@ include file="../components/footer/footer.jsp" %>
</body>
</html>