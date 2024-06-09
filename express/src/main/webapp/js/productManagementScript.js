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
                        <a href="/express/ProductController?action=EDIT&id=${product.product_id}">Düzenle</a>
                        <a href="/express/ProductController?action=DELETE&id=${product.product_id}">Sil</a>
                    </td>
                `;
                productList.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching products:", error));
}

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('logout-button').addEventListener('click', function () {
        window.location.href = "/express/SupplierController?action=LOGOUT";
    });
});
