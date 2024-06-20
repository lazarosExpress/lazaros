document.addEventListener('DOMContentLoaded', function () {
    const basketIcon = document.getElementById("basket-icon");
    const dropdownBasket = document.getElementById("dropdown-basket");
    const dropdownUserMenu = document.getElementById("user-menu");
    const searchForm = document.getElementById('search-form');
    const searchInput = document.getElementById('search-input');
    const searchResults = document.getElementById('search-results');

    searchForm.addEventListener('submit', function (event) {
        event.preventDefault();
        const query = searchInput.value.trim();
        if (query) {
            performSearch(query);
        }
    });

    const performSearch = (query) => {
        fetch(`ProductController?action=SEARCH&query=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                displaySearchResults(data);
            });
    };

    const displaySearchResults = (products) => {
        if (products.length > 0) {
            searchResults.innerHTML = '<ul>' + products.map(product => `
                <li>
                    <a href="ProductController?action=DETAILS&id=${product.product_id}">${product.product_name}</a>
                </li>
            `).join('') + '</ul>';
            searchResults.style.display = 'block';
        } else {
            searchResults.innerHTML = '<p>Sonuç bulunamadı.</p>';
            searchResults.style.display = 'block';
        }
    };

    document.addEventListener("click", function (event) {
        if (!searchResults.contains(event.target) && !searchForm.contains(event.target)) {
            searchResults.style.display = "none";
        }
    });
    const toggleDropdown = (element) => {
        element.style.display = element.style.display === "block" ? "none" : "block";
    };

    document.addEventListener("click", function (event) {
        if (!dropdownBasket.contains(event.target) && !basketIcon.contains(event.target)) {
            dropdownBasket.style.display = "none";
        }
        if (!dropdownUserMenu.contains(event.target) && !document.getElementById("user-icon").contains(event.target)) {
            dropdownUserMenu.style.display = "none";
        }
    });

    basketIcon.addEventListener('click', function (event) {
        event.preventDefault();
        toggleDropdown(dropdownBasket);
        loadBasket();
    });

    document.getElementById("user-icon").addEventListener('click', function (event) {
        event.preventDefault();
        toggleDropdown(dropdownUserMenu);
    });

    const loadBasket = () => {
        fetch('BasketController?action=LISTBASKET')
            .then(response => response.json())
            .then(data => {
                console.log('Basket Data:', data);
                if (data.length > 0) {
                    dropdownBasket.innerHTML = '';
                    let totalPrice = 0;
                    data.forEach(item => {
                        const product = item.product;
                        totalPrice += product.product_prize * item.basket_qty;
                        dropdownBasket.innerHTML += `
                            <div class="basket-item">
                                <img src="productImg/${product.product_imgUrl}" alt="${product.product_name}">
                                <div class="basket-item-info">
                                    <h3>${product.product_name}</h3>
                                    <p>Adet: <span class="item-qty">${item.basket_qty}</span></p>
                                    <p class="price">${product.product_prize} TL</p>
                                    <button class="qty-btn decrease-qty" data-id="${item.product_id}">-</button>
                                    <button class="qty-btn increase-qty" data-id="${item.product_id}">+</button>
                                </div>
                                <button class="remove-btn" data-id="${item.basket_id}">Kaldır</button>
                            </div>
                        `;
                    });
                    dropdownBasket.innerHTML += `
                        <div class="basket-footer">
                            <p class="total-price" >Toplam Fiyat: ${totalPrice.toFixed(2)} TL</p>
                            <a class="complete-order-btn">Siparişi Tamamla</a>
                        </div>
                    `;
                } else {
                    dropdownBasket.innerHTML = '<p>Sepetiniz boş.</p>';
                }
                addEventListenersToButtons();
            });
    };

    const addEventListenersToButtons = () => {
        const increaseQtyButtons = document.querySelectorAll('.increase-qty');
        const decreaseQtyButtons = document.querySelectorAll('.decrease-qty');
        const removeButtons = document.querySelectorAll('.remove-btn');
        const completeOrderButton = document.querySelector('.complete-order-btn');

        increaseQtyButtons.forEach(button => {
            button.addEventListener('click', function () {
                const productId = this.getAttribute('data-id');
                fetch(`BasketController?action=INCREASE&id=${productId}`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' }
                }).then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            loadBasket();
                        }
                    });
            });
        });

        decreaseQtyButtons.forEach(button => {
            button.addEventListener('click', function () {
                const productId = this.getAttribute('data-id');
                fetch(`BasketController?action=DECREASE&id=${productId}`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' }
                }).then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            loadBasket();
                        }
                    });
            });
        });

        removeButtons.forEach(button => {
            button.addEventListener('click', function () {
                const basketId = this.getAttribute('data-id');
                fetch(`BasketController?action=DELETE&id=${basketId}`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' }
                }).then(response => response.json())
                    .then(data => {
                        if (data.success) {
                            loadBasket();
                        }
                    });
            });
        });

        if (completeOrderButton) {
            completeOrderButton.addEventListener('click', function (event) {
                event.preventDefault(); // Bu satırı kaldırabilirsiniz, çünkü bu butonun bir link olduğunu farz ediyoruz.
                window.location.href = "/express/views/orderPage.jsp"; // Yönlendirme işlemini gerçekleştirin.
            });
        }
    };


    const addToBasket = (productId) => {
        fetch(`BasketController?action=ADD&id=${productId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }).then(response => response.json())
            .then(data => {
                if (data.success) {
                    loadBasket();
                }
            });
    };

    document.querySelectorAll('.add-to-basket-btn').forEach(button => {
        button.addEventListener('click', function (event) {
            event.preventDefault();
            const productId = this.getAttribute('data-id');
            addToBasket(productId);
        });
    });

    document.getElementById('logout-button').addEventListener('click', function () {
        window.location.href = "CustomerController?action=LOGOUT";
    });
});