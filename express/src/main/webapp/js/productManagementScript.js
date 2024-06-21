document.addEventListener("DOMContentLoaded", function () {
    fetchProducts();
});

function fetchProducts() {
    fetch("/express/ProductController?action=LIST_JSON")
        .then(response => response.json())
        .then(data => {
            const productList = document.getElementById("productList");
            productList.innerHTML = "";
            data.forEach(product => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${product.product_name}</td>
                    <td>${product.product_prize}</td>
                    <td>${product.product_stock}</td>
                    <td>${product.brand_name}</td>
                    <td>${product.category_name}</td>
                    <td>${product.product_explanation}</td>
                    <td>${product.product_properties}</td>
                    <td><img src="${window.location.origin}${window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2))}/productImg/${product.product_imgUrl}" alt="Product Image" width="50"></td>
                    <td>
                        <button onclick="viewProductDetails(${product.product_id})">Düzenle</button>
                        <button onclick="deleteProduct(${product.product_id})">Sil</button>
                    </td>
                `;
                productList.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching products:", error));
}
const viewProductDetails = (productId) => {
    window.location.href = `updateProductManagement.jsp?productId=${productId}`;
};
function deleteProduct(productId) {
    if (confirm("Bu ürünü silmek istediğinize emin misiniz?")) {
        fetch(`/express/ProductController?action=DELETE&id=${productId}`, {
            method: "POST",
        })
            .then(response => {
                if (response.ok) {
                    fetchProducts();  // Listeyi yeniden yükle
                } else {
                    console.error("Error deleting product:", response.statusText);
                }
            })
            .catch(error => console.error("Error deleting product:", error));
    }
}
