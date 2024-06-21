document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    const productId = params.get('productId');
    if (productId != null) {
        loadProductDetails(productId);
    } else {
        alert("Product ID is missing in the URL");
    }
});

function loadProductDetails(productId) {
    fetch(`http://localhost:8080/express/ProductController?action=UPDATEDETAILS&id=${productId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Ürün detayları yüklenemedi');
            }
            return response.json();
        })
        .then(product => {
            document.getElementById('product_id').value = product.product_id;
            document.getElementById('product_name').value = product.product_name;
            document.getElementById('product_prize').value = product.product_prize;
            document.getElementById('product_stock').value = product.product_stock;
            document.getElementById('brand_name').value = product.brand_name;
            document.getElementById('product_explanation').value = product.product_explanation;
            document.getElementById('product_properties').value = product.product_properties;
            document.getElementById('category_id').value = product.category_id;
        })
        .catch(error => console.error("Error loading product details:", error));
}

function updateProduct() {
    const formData = new FormData(document.getElementById('productForm'));
    const productId = document.getElementById('product_id').value; // Formdaki gizli alan üzerinden alıyoruz
    fetch(`http://localhost:8080/express/ProductController?action=UPDATE&id=${productId}`, {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                alert('Ürün başarıyla güncellendi.');
                window.location.href = '/express/admin/productManagement.jsp';
            } else {
                alert('Ürün güncellenirken bir hata oluştu.');
            }
        })
        .catch(error => console.error("Error updating product:", error));
}
document.addEventListener("DOMContentLoaded", function () {
    fetch("http://localhost:8080/express/ProductController?action=GET_CATEGORIES")
        .then(response => response.json())
        .then(data => {
            const categorySelect = document.getElementById("category_id");
            data.forEach(category => {
                const option = document.createElement("option");
                option.value = category.category_id;
                option.text = category.category_name;
                categorySelect.appendChild(option);
            });
        })
        .catch(error => console.error("Error loading categories:", error));
});