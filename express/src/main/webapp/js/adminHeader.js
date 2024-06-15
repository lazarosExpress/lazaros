document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('logout-button').addEventListener('click', function (event) {
        event.preventDefault(); // Varsayılan bağlantı davranışını engeller
        window.location.href = "/express/SupplierController?action=LOGOUT";
    });
});
