function addToCart(productId) {
    fetch(contextPath + '/BasketController?action=ADD&id=' + productId, {
        method: 'GET'
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {} else {
                alert('Ürün sepete eklenirken bir hata oluştu.');
            }
        })
        .catch(error => console.error('Error adding to cart:', error));
}
