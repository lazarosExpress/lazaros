const loadOrders = () => {
    fetch('http://localhost:8080/express/OrderController?action=LISTORDERS')
        .then(response => response.json())
        .then(data => {
            const ongoingOrdersTable = document.getElementById('ongoingOrdersTable').querySelector('tbody');
            const completedOrdersTable = document.getElementById('completedOrdersTable').querySelector('tbody');
            ongoingOrdersTable.innerHTML = '';
            completedOrdersTable.innerHTML = '';

            data.ongoing.forEach(order => {
                ongoingOrdersTable.innerHTML += `
                    <tr>
                        <td>${order.product_name}</td>
                        <td>${order.customer_name}</td>
                        <td>${order.status}</td>
                    </tr>
                `;
            });

            data.completed.forEach(order => {
                completedOrdersTable.innerHTML += `
                    <tr>
                        <td>${order.productName}</td>
                        <td>${order.customerFirstName}</td>
                    </tr>
                `;
            });
        })
        .catch(error => console.error('Error loading orders:', error));
};

document.addEventListener('DOMContentLoaded', loadOrders);
