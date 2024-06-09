document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('logout-button').addEventListener('click', function (event) {
        event.preventDefault(); // Varsayılan bağlantı davranışını engeller
        window.location.href = "/express/SupplierController?action=LOGOUT";
    });
});
document.addEventListener("DOMContentLoaded", function () {
    fetchCustomers();
});

function fetchCustomers() {
    fetch("/express/CustomerController?action=LIST_JSON")
        .then(response => response.json())
        .then(data => {
            const productList = document.getElementById('product-list');
            data.forEach(product => {
                const productHtml = `
                        <div class="col-md-6 col-lg-4 col-xl-3">
                            <div class="single-product">
                                <div class="part-1">
                                    <img src="product-image/${product.product_imgUrl}" alt="Product Image">
                                    <ul>
                                        <li><a href="add-to-cart?id=${product.id}" class="cart-btn"><i class="fas fa-shopping-cart"></i></a></li>
                                        <li><a href="#"><i class="fas fa-heart"></i></a></li>
                                        <li><a href="#"><i class="fas fa-plus"></i></a></li>
                                        <li><a href="#"><i class="fas fa-expand"></i></a></li>
                                    </ul>
                                </div>
                                <div class="part-2">
                                    <h3 class="product-title">${product.product_name}</h3>
                                    <h3 class="product-title">${product.category_name}</h3>
                                    <h4 class="product-old-price">79.99₺</h4>
                                    <h4 class="product-price">${product.product_prize}₺</h4>
                                </div>
                            </div>
                        </div>
                    `;
                productList.insertAdjacentHTML('beforeend', productHtml);
            });
        })
        .catch(error => console.error('Error fetching products:', error));
};

