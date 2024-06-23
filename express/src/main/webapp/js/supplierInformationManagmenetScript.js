document.addEventListener('DOMContentLoaded', function() {
    fetchUserInfo();
});

function fetchUserInfo() {
    fetch('http://localhost:8080/express/SupplierController?action=FETCH_USER_INFO')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('first_name').value = data.supplier_firstName;
            document.getElementById('last_name').value = data.supplier_lastName;
            document.getElementById('shop_name').value = data.supplier_shopName;
            document.getElementById('iban').value = data.supplier_iban;
            document.getElementById('email').value = data.supplier_eMail;
            document.getElementById('phone').value = data.supplier_phoneNumber;
        })
        .catch(error => console.error('Error fetching user info:', error));
}

function updateUserInfo() {
    const userInfo = {
        supplier_firstName: document.getElementById('first_name').value,
        supplier_lastName: document.getElementById('last_name').value,
        supplier_shopName: document.getElementById('shop_name').value,
        supplier_iban: document.getElementById('iban').value,
        supplier_eMail: document.getElementById('email').value,
        supplier_phoneNumber: document.getElementById('phone').value
    };

    fetch('http://localhost:8080/express/SupplierController?action=UPDATE_USER_INFO', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userInfo)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
    })
    .catch(error => console.error('Error updating user info:', error));
}

function updatePassword() {
    const passwordInfo = {
        currentPassword: document.getElementById('current_password').value,
        newPassword: document.getElementById('new_password').value,
        confirmPassword: document.getElementById('confirm_password').value
    };

    fetch('http://localhost:8080/express/SupplierController?action=UPDATE_PASSWORD', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(passwordInfo)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
    })
    .catch(error => console.error('Error updating password:', error));
}

function deleteAccount() {
    const deleteInfo = {
        email: document.getElementById('delete_email').value,
        password: document.getElementById('delete_password').value
    };

    fetch('http://localhost:8080/express/SupplierController?action=DELETE_ACCOUNT', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(deleteInfo)
    })
    .then(response => response.text())
    .then(data => {
        alert(data);
        if (data === "Hesap başarıyla silindi.") {
            window.location.href = "http://localhost:8080/express/views/login/login.jsp";
        }
    })
    .catch(error => console.error('Error deleting account:', error));
}
