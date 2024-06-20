const loadCustomerOrders = () => {
    fetch('http://localhost:8080/express/OrderController?action=LISTCUSTOMERORDERS')
        .then(response => response.json())
        .then(data => {
            const ordersContainer = document.querySelector('.orders-container');
            ordersContainer.innerHTML = '';

            data.forEach(order => {
                ordersContainer.innerHTML += `
                    <div class="order-card" data-status="${order.status}">
                        <div class="order-summary">
                            <p><strong>Sipariş Tarihi:</strong> ${order.order_date}</p>
                            <p><strong>Alıcı:</strong> ${order.customer_name}</p>
                            <p><strong>Tutar:</strong> ${order.total_price} TL</p>
                            <button onclick="viewOrderDetails(${order.order_id})">Sipariş Detayı</button>
                        </div>
                        <div class="product-info">
                            <p class="status">${order.status}</p>
                            <p>${order.product_qty} ürün ${order.delivery_date} tarihinde teslim edilmiştir.</p>
                            <img src="${order.product_imgUrl}" alt="Ürün Fotoğrafı">
                            <p class="shipping-info">Kargo Bilgisi</p>
                            </div>
                        </div>
                    `;
            });
        })
        .catch(error => console.error('Error loading customer orders:', error));
};

const viewOrderDetails = (orderId) => {
    // Implement the logic to view order details
    // You might want to open a modal or redirect to a detailed order page
};

document.addEventListener('DOMContentLoaded', loadCustomerOrders);

const filterOrders = (status) => {
    const orders = document.querySelectorAll('.order-card');
    orders.forEach(order => {
        if (status === 'all' || order.getAttribute('data-status') === status) {
            order.style.display = '';
        } else {
            order.style.display = 'none';
        }
    });
};

document.getElementById('search-bar').addEventListener('input', function () {
    const searchText = this.value.toLowerCase();
    const orders = document.querySelectorAll('.order-card');
    orders.forEach(order => {
        const productName = order.querySelector('.product-info p').textContent.toLowerCase();
        if (productName.includes(searchText)) {
            order.style.display = '';
        } else {
            order.style.display = 'none';
        }
    });
});

