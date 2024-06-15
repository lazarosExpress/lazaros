document.addEventListener("DOMContentLoaded", function () {
    fetchCustomers();
});

function fetchCustomers() {
    fetch("/express/CustomerController?action=LIST_JSON")
        .then(response => response.json())
        .then(data => {
            const customerList = document.getElementById("customerList");
            customerList.innerHTML = "";
            data.forEach(customer => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${customer.customer_firstName}</td>
                    <td>${customer.customer_lastName}</td>
                    <td>${customer.customer_eMail}</td>
                    <td>${customer.customer_phoneNumber}</td>
                    <td>
                        <a href="/express/CustomerController?action=EDIT&id=${customer.customer_id}">Düzenle</a>
                        <button onclick="deleteCustomer(${customer.customer_id})">Sil</button>
                    </td>
                `;
                customerList.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching customers:", error));
}

function deleteCustomer(customerId) {
    if (confirm("Bu ürünü silmek istediğinize emin misiniz?")) {
        fetch(`/express/CustomerController?action=DELETE&id=${customerId}`, {
            method: "POST",
        })
            .then(response => {
                if (response.ok) {
                    fetchCustomers();  // Listeyi yeniden yükle
                } else {
                    console.error("Error deleting customer:", response.statusText);
                }
            })
            .catch(error => console.error("Error deleting customer:", error));
    }
}