<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="tr">

<head>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="${pageContext.request.contextPath}/js/addProductManagementScript.js"></script>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addProductManagementStyle.css" />

</head>

<body>
    <header>
        <div>
            <a href="${pageContext.request.contextPath}/admin/supplierAdminPanel.jsp" class="back-button">
                <i class="bi bi-arrow-left"></i>
            </a>
        </div>
        <img src="${pageContext.request.contextPath}/images/lazarosSupplier.png" alt="Logo" />
        <div class="logout">
            <i id="logout-button" class="bi bi-box-arrow-right"></i>
        </div>
    </header>

    <section class="container">
        <div class="card">
            <h3>Ürün Oluştur</h3>
            <form id="productForm" method="post" action="/express/ProductController?action=ADD" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="product_name">Ürün Adı:</label>
                    <input name="product_name" type="text" id="product_name" required />
                </div>
                <div class="form-group">
                    <label for="product_prize">Fiyat:</label>
                    <input name="product_prize" type="number" id="product_prize" required />
                </div>
                <div class="form-group">
                    <label for="product_stock">Stok Miktarı:</label>
                    <input name="product_stock" type="number" id="product_stock" required />
                </div>
                <div class="form-group">
                    <label for="brand_name">Marka:</label>
                    <input name="brand_name" type="text" id="brand_name" required />
                </div>
                <div class="form-group">
                    <label for="product_explanation">Ürün Açıklaması:</label>
                    <textarea name="product_explanation" id="product_explanation" rows="4" style="width: 100%" required></textarea>
                </div>
                <div class="form-group">
                    <label for="product_properties">Ürün Özellikleri:</label>
                    <textarea name="product_properties" id="product_properties" rows="4" style="width: 100%" required></textarea>
                </div>
                <div class="form-group">
                    <label for="category_id">Kategori:</label>
                    <select name="category_id" id="category_id" required>
                        <!-- Kategoriler buraya yüklenecek -->
                    </select>
                </div>
                <div class="form-group">
                    <label for="product_imgUrl">Ürün Fotoğrafı:</label>
                    <input name="product_imgUrl" type="file" id="product_imgUrl" accept="image/*" required />
                </div>
                <button type="submit" class="btn-primary">Oluştur</button>
            </form>
        </div>
    </section>
</body>

</html>
