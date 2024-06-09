document.addEventListener("DOMContentLoaded", function () {
    fetchProducts();
});

function fetchProducts() {
    fetch("/express/ProductController?action=LIST")
        .then(response => response.json())
        .then(data => {
            const productList = document.getElementById("productList");
            productList.innerHTML = "";
            data.forEach(product => {
                const row = document.createElement("div");
                row.innerHTML = `
                <div class="col-md-6 col-lg-4 col-xl-3">
                    <div class="single-product">
                        <div class="part-1">
                            <img src="${window.location.origin}${window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2))}/productImg/${product.product_imgUrl}" alt="Product Image">
                            <ul>
                                <li><a href="add-to-cart?id=${product.product_id}" class="cart-btn"><i class="fas fa-shopping-cart"></i></a></li>
                                <li><a href="#"><i class="fas fa-heart"></i></a></li>
                                <li><a href="#"><i class="fas fa-plus"></i></a></li>
                                <li><a href="#"><i class="fas fa-expand"></i></a></li>
                            </ul>
                        </div>
                        <div class="part-2">
                            <h3 style="color: black" class="product-title">${product.product_name}</h3>
                            <h3 style="color: black" class="product-title">${product.category_name}</h3>
                            <h4 class="product-old-price">79.99₺</h4>
                            <h4 class="product-price">${product.product_prize}₺</h4>
                        </div>
                    </div>
                </div>
                `;
                productList.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching products:", error));
}
