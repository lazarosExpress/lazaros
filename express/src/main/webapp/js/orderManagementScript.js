const changeOrderStatus = (orderId) => {
    const currentStatus = document.querySelector(`tr[data-order-id="${orderId}"]`).getAttribute('data-order-status');
    const newStatus = currentStatus === 'true' ? false : true;

    fetch('http://localhost:8080/express/OrderController', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
            action: 'CHANGEORDERSTATUS',
            orderId: orderId,
            newStatus: newStatus,
        }),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.text();
    })
    .then(result => {
        console.log(result);
        loadOrders(); // Durum değişikliğinden sonra siparişleri yeniden yükleyin
    })
    .catch(error => console.error('Error changing order status:', error));
};

const loadOrders = () => {
    fetch('http://localhost:8080/express/OrderController?action=LISTSUPPLIERORDERS')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const ongoingOrdersTable = document.getElementById('ongoingOrdersTable').querySelector('tbody');
            const completedOrdersTable = document.getElementById('completedOrdersTable').querySelector('tbody');
            ongoingOrdersTable.innerHTML = '';
            completedOrdersTable.innerHTML = '';

            data.forEach(order => {
                const orderRow = `
                    <tr data-order-id="${order.order_id}" data-order-status="${order.order_state}">
                        <td>${(order.order_totalPrice).toFixed(2)}</td>
                        <td>${order.customerFirstName} ${order.customerLastName}</td>
                        <td>
                            <button onclick="getSupplerOrderDetail(${order.order_id},${order.sellerId})">Detay</button>
                            <button onclick="changeOrderStatus(${order.order_id})">Durum Değiştir</button>
                        </td>
                    </tr>
                `;
                if (order.order_state) {
                    completedOrdersTable.innerHTML += orderRow;
                } else {
                    ongoingOrdersTable.innerHTML += orderRow;
                }
            });
        })
        .catch(error => console.error('Error loading orders:', error));
};

document.addEventListener('DOMContentLoaded', loadOrders);

function getSupplerOrderDetail(orderId, supplierID) {
    window.location.href = `/express/admin/orderDetailManagement.jsp?orderId=${orderId}&supplierId=${supplierID}`;
}
