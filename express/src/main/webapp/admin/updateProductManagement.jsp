<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="tr">

<head>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="${pageContext.request.contextPath}/js/updateProductManagementScript.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateProductManagementStyle.css" />
</head>

<%@ include file="../components/adminHeader/adminHeader.jsp" %>
<%@ include file="../components/footer/footer.jsp" %>

<body>
    <section class="container">
        <div class="card">
            <h3>Ürün Güncelle</h3>
            <form id="productForm" method="post" enctype="multipart/form-data">
                <input type="hidden" id="product_id" name="product_id">
                <div class="form-group">
                    <label for="product_name">Ürün Adı:</label>
                    <input name="product_name" type="text" id="product_name" required />
                </div>
                <div class="form-group">
                    <label for="product_oldPrice">Liste Fiyatı:</label>
                    <input name="product_oldPrice" type="number" id="product_oldPrice" step="0.01" required />
                </div>
                <div class="form-group">
                    <label for="product_price">İndirimli Fiyatı:</label>
                    <input name="product_price" type="number" id="product_price" step="0.01" required />
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
                    <input name="product_imgUrl" type="file" id="product_imgUrl" accept="image/*" />
                </div>
                <button type="button" class="btn-primary" onclick="updateProduct()">Güncelle</button>
            </form>
        </div>
    </section>
</body>
</html>
