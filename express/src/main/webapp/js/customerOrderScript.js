
document.addEventListener('DOMContentLoaded', () => {
    const searchBar = document.getElementById('search-bar');
    searchBar.addEventListener('keyup', filterOrdersByName);
});

function filterOrders(status) {
    const orderCards = document.querySelectorAll('.order-card');
    orderCards.forEach(card => {
        if (status === 'all' || card.dataset.status === status) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }
    });
}

function filterOrdersByName() {
    const searchValue = document.getElementById('search-bar').value.toLowerCase();
    const orderCards = document.querySelectorAll('.order-card');
    orderCards.forEach(card => {
        const orderSummary = card.querySelector('.order-summary').innerText.toLowerCase();
        if (orderSummary.includes(searchValue)) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }
    });
}
