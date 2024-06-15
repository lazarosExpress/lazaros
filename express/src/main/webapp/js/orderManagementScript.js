const ongoingOrders = [
    { product: "Ürün 1", customer: "Müşteri A", status: "Hazırlanıyor" },
    { product: "Ürün 2", customer: "Müşteri B", status: "Kargoda" },
    { product: "Ürün 3", customer: "Müşteri C", status: "Hazırlanıyor" }
];

const ongoingOrdersTable = document.getElementById('ongoingOrdersTable').getElementsByTagName('tbody')[0];
ongoingOrders.forEach(order => {
    const newRow = ongoingOrdersTable.insertRow();
    const productCell = newRow.insertCell(0);
    const customerCell = newRow.insertCell(1);
    const statusCell = newRow.insertCell(2);

    productCell.innerHTML = `<a href="urun_detay.html?product=${order.product}">${order.product}</a>`;
    customerCell.innerHTML = `<a href="musteri_detay.html?customer=${order.customer}">${order.customer}</a>`;
    statusCell.textContent = order.status;
});

// Tamamlanmış Siparişler için örnek veri ekleyelim
const completedOrders = [
    { product: "Ürün 4", customer: "Müşteri D" },
    { product: "Ürün 5", customer: "Müşteri E" }
];

const completedOrdersTable = document.getElementById('completedOrdersTable').getElementsByTagName('tbody')[0];
completedOrders.forEach(order => {
    const newRow = completedOrdersTable.insertRow();
    const productCell = newRow.insertCell(0);
    const customerCell = newRow.insertCell(1);

    productCell.innerHTML = `<a href="urun_detay.html?product=${order.product}">${order.product}</a>`;
    customerCell.innerHTML = `<a href="musteri_detay.html?customer=${order.customer}">${order.customer}</a>`;
});

