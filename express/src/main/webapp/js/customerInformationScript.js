document.addEventListener('DOMContentLoaded', function() {
    fetchUserInfo();
});

function fetchUserInfo() {
    fetch('http://localhost:8080/express/CustomerController?action=FETCH_USER_INFO')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            document.getElementById('first_name').value = data.customer_firstName;
            document.getElementById('last_name').value = data.customer_lastName;
            document.getElementById('email').value = data.customer_eMail;
            document.getElementById('phone').value = data.customer_phoneNumber;
        })
        .catch(error => console.error('Error fetching user info:', error));
}

function updateUserInfo() {
    const userInfo = {
        firstName: document.getElementById('first_name').value,
        lastName: document.getElementById('last_name').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value
    };

    fetch('http://localhost:8080/express/CustomerController?action=UPDATE_USER_INFO', {
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

    fetch('http://localhost:8080/express/CustomerController?action=UPDATE_PASSWORD', {
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

    fetch('http://localhost:8080/express/CustomerController?action=DELETE_ACCOUNT', {
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
