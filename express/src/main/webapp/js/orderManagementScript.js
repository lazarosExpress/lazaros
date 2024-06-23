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
                    <tr>
                        <td>${(order.order_totalPrice).toFixed(2)}</td>
                        <td>${order.customerFirstName} ${order.customerLastName}</td>
                        <td><button onclick="getSupplerOrderDetail(${order.order_id},${order.sellerId})">Detay</button></td>
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

function getSupplerOrderDetail(orderId, supplerID) {
    window.location.href = `/express/admin/orderDetailManagement.jsp?orderId=${orderId}&supplierId=${supplerID}`;
}
