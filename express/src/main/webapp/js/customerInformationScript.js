document.addEventListener('DOMContentLoaded', function() {
    // Örnek kullanıcı verileri Dinamiği buraya ayarla :D
    const userData = {
        first_name: "Berkay",
        last_name: "Karagöz",
        email: "ber*******3@hotmail.com",
        phone: "5******",
        birth_date: "2002-02-21"
    };

    // Form elementlerini doldur
    document.getElementById('first_name').value = userData.first_name;
    document.getElementById('last_name').value = userData.last_name;
    document.getElementById('email').value = userData.email;
    document.getElementById('phone').value = userData.phone;
    document.getElementById('birth_date').value = userData.birth_date;

    // Bu kısım alert buton işlevi varmı diye denedim.
    document.getElementById('userForm').addEventListener('submit', function(event) {
        event.preventDefault();
        alert('Bilgiler güncellendi!');
    });
});
