document.addEventListener("DOMContentLoaded", function () {
    const params = new URLSearchParams(window.location.search);
    const customerId = params.get('customerId');
    if (customerId) {
        loadCustomerDetails(customerId);
    } else {
        alert("URL'de müşteri ID'si eksik.");
    }
});

function loadCustomerDetails(customerId) {
    fetch(`http://localhost:8080/express/CustomerController?action=UPDATEDETAILSWITHSUPPLIER&customerId=${customerId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Müşteri detayları yüklenemedi.');
            }
            return response.json();
        })
        .then(customer => {
            document.getElementById('customer_id').value = customer.customer_id;
            document.getElementById('customer_firstName').value = customer.customer_firstName;
            document.getElementById('customer_lastName').value = customer.customer_lastName;
            document.getElementById('customer_eMail').value = customer.customer_eMail;
            document.getElementById('customer_phoneNumber').value = customer.customer_phoneNumber;
        })
        .catch(error => console.error("Müşteri detayları yüklenirken hata oluştu:", error));
}

function updateCustomer() {
    const customerId = document.getElementById('customer_id').value;
    const customerData = {
        customer_firstName: document.getElementById('customer_firstName').value,
        customer_lastName: document.getElementById('customer_lastName').value,
        customer_eMail: document.getElementById('customer_eMail').value,
        customer_phoneNumber: document.getElementById('customer_phoneNumber').value,
    };

    fetch(`http://localhost:8080/express/CustomerController?action=UPDATESUPPLIER&customerId=${customerId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(customerData)
    })
        .then(response => {
            if (response.ok) {
                alert('Müşteri başarıyla güncellendi.');
                window.location.href = '/express/admin/customerManagement.jsp';
            } else {
                return response.text().then(text => { throw new Error(text); });
            }
        })
        .catch(error => alert("Müşteri güncellenirken hata oluştu: " + error.message));
}


