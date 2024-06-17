document.addEventListener('DOMContentLoaded', function () {
    loadBasket();
    fetchAddresses();

    document.querySelector('.checkout-cta').addEventListener('click', function () {
        fetch('http://localhost:8080/express/BasketController?action=COMPLETE', {
            method: 'POST'
        }).then(response => {
            if (response.ok) {
                window.location.href = 'orders.jsp';
            }
        }).catch(error => {
            console.error('Error:', error);
        });
    });

    document.querySelector('.promo-code-cta').addEventListener('click', applyPromoCode);
});

const loadBasket = () => {
    fetch('http://localhost:8080/express/BasketController?action=LISTBASKET')
        .then(response => {
            console.log('HTTP Response Status:', response.status);
            if (!response.ok) {
                console.error('Network response was not ok', response.statusText);
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Basket Data:', data);
            if (data.length > 0) {
                let basketHtml = '';
                let totalPrice = 0;
                data.forEach(item => {
                    const product = item.product;
                    const subtotal = product.product_prize * item.basket_qty;
                    totalPrice += subtotal;
                    basketHtml += `
                        <div class="basket-product">
                            <div class="item">
                                <div class="product-image">
                                    <img src="/express/productImg/${product.product_imgUrl}" alt="${product.product_name}" class="product-frame">
                                </div>
                                <div class="product-details">
                                    <h1><strong><span class="item-quantity">${item.basket_qty}</span> x ${product.product_name}</strong></h1>
                                    <p>Mağaza: <a>${product.supplier_shopName}</a></p>
                                </div>
                            </div>
                            <div class="price">${product.product_prize.toFixed(2)}</div>
                            <div class="quantity">
                                <input type="number" value="${item.basket_qty}" min="1" class="quantity-field" data-product-id="${product.product_id}">
                            </div>
                            <div class="subtotal">${subtotal.toFixed(2)}</div>
                            <div class="remove">
                                <button onclick="removeItem(${item.basket_id})">Kaldır</button>
                            </div>
                        </div>
                    `;
                });
                document.getElementById('basket-products').innerHTML = basketHtml;
                document.getElementById('basket-subtotal').innerText = totalPrice.toFixed(2);
                document.getElementById('basket-total').innerText = totalPrice.toFixed(2);

                document.querySelectorAll('.quantity-field').forEach(input => {
                    input.addEventListener('change', function () {
                        updateQuantity(this);
                    });
                });
            } else {
                document.getElementById('basket-products').innerHTML = '<p>Sepetiniz boş.</p>';
            }
        })
        .catch(error => {
            console.error('Error fetching basket data:', error);
            alert('An error occurred while fetching basket data.');
        });
};

const updateQuantity = (input) => {
    const productId = input.getAttribute('data-product-id');
    const quantity = input.value;
    if (quantity <= 0) {
        removeItem(productId);
    } else {
        fetch(`http://localhost:8080/express/BasketController?action=UPDATEQUANTITY&productId=${productId}&quantity=${quantity}`, {
            method: 'POST'
        }).then(response => {
            if (response.ok) {
                loadBasket();
            }
        }).catch(error => {
            console.error('Error:', error);
        });
    }
};

const removeItem = (basketId) => {
    fetch(`http://localhost:8080/express/BasketController?action=DELETE&id=${basketId}`, {
        method: 'POST'
    }).then(response => {
        if (response.ok) {
            loadBasket();
        } else {
            alert('Ürün sepetten çıkarılamadı.');
        }
    }).catch(error => {
        console.error('Error:', error);
    });
};

const applyPromoCode = () => {
    const promoCode = document.getElementById('promo-code').value;
    if (promoCode === '10OFF') {
        const discount = 10;
        const subtotalElement = document.getElementById('basket-subtotal');
        const subtotal = parseFloat(subtotalElement.innerText);
        const newTotal = subtotal - discount;
        subtotalElement.innerText = newTotal.toFixed(2) + '₺';
        document.getElementById('basket-total').innerText = newTotal.toFixed(2) + '₺';
        document.querySelector('.summary-promo').classList.remove('hide');
        document.getElementById('basket-promo').innerText = discount.toFixed(2) + '₺';
    } else {
        alert('Geçersiz promo kodu');
    }
};



function fetchAddresses() {
    fetch('http://localhost:8080/express/AddressController?action=list')
        .then(response => response.json())
        .then(addresses => populateAddressDropdown(addresses))
        .catch(error => console.error('Error fetching addresses:', error));
}

function populateAddressDropdown(addresses) {
    const addressSelect = document.querySelector('.summary-delivery-selection');
    addressSelect.innerHTML = ''; // Clear existing options

    const defaultOption = document.createElement('option');
    defaultOption.value = "0";
    defaultOption.textContent = "Select Address";
    addressSelect.appendChild(defaultOption);

    addresses.forEach(address => {
        const option = document.createElement('option');
        option.value = address.address_id; 
        option.textContent = `${address.address_description}, ${address.address_title}`; // Customize as needed
        addressSelect.appendChild(option);
    });
}

