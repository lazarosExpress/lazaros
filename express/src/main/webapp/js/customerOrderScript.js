const loadCustomerOrders = () => {
    fetch('http://localhost:8080/express/OrderController?action=LISTCUSTOMERORDERS')
        .then(response => response.json())
        .then(data => {
            const ordersContainer = document.querySelector('.orders-container');
            ordersContainer.innerHTML = '';

            data.forEach(order => {
                let orderStatus = '';
                if (order.order_state == 0) {
                    orderStatus = 'Devam Ediyor';
                } else if (order.order_state == 1) {
                    orderStatus = 'Tamamlandı';
                }

                ordersContainer.innerHTML += `
                    <div class="order-card" data-status="${order.order_state}">
                        <div class="order-summary">
                            <p class="status">${orderStatus}</p>                    
                            <p><strong>Sipariş Tarihi:</strong> ${order.order_date}</p>
                            <p><strong>Alıcı:</strong> ${order.customerFirstName} ${order.customerLastName}</p>
                            <p><strong>Tutar:</strong> ${(order.order_totalPrice).toFixed(2)} TL</p>
                            <button onclick="viewOrderDetails(${order.order_id})">Sipariş Detayı</button>
                        </div>
                    </div>
                `;
            });
        })
        .catch(error => console.error('Error loading customer orders:', error));
};

const viewOrderDetails = (orderId) => {
    window.location.href = `orderDetail.jsp?orderId=${orderId}`;
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
