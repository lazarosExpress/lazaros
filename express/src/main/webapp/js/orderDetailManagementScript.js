document.addEventListener('DOMContentLoaded', function () {
    const params = new URLSearchParams(window.location.search);
    const orderId = params.get('orderId');
    const supplierId = params.get('supplierId');

    fetch(`http://localhost:8080/express/OrderController?action=ORDERMANAGEMENTDETAILS&orderId=${orderId}&supplierId=${supplierId}`)
        .then(response => response.json())
        .then(data => {
            let orderSummaryHTML = '';

            const groupedBySeller = data.reduce((acc, item) => {
                if (!acc[item.supplierId]) {
                    acc[item.supplierId] = {
                        sellerName: item.supplierShopName,
                        items: []
                    };
                }
                acc[item.supplierId].items.push(item);
                return acc;
            }, {});

            for (let supplierId in groupedBySeller) {
                const seller = groupedBySeller[supplierId];
                orderSummaryHTML += `
                    <div class="seller-section">
                        ${seller.items.map(item => `
                            <div class="product-row">
                                <img src="${window.location.origin}${window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2))}/productImg/${item.productImgUrl}" alt="${item.productImgUrl}" class="product-image">
                                <div class="product-details">
                                    <p><strong>Ürün İsmi:</strong> ${item.productName}</p>
                                    <p><strong>Satın Alınan Adedi:</strong> ${item.productQty}</p>
                                    <p><strong>Ürün Fiyatı:</strong> ${item.productPrice.toFixed(2)} TL</p>
                                    <p><strong>Ürünün Toplam Fiyatı:</strong> ${(item.productQty * item.productPrice).toFixed(2)} TL</p>
                                    <p><strong>Satıcı:</strong> ${item.sellerName}</p>
                                    </div>
                            </div>
                        `).join('')}
                        <div class="total-amount">
                            <p><strong>Toplam Tutar:</strong> ${seller.items.reduce((acc, item) => acc + item.productQty * item.productPrice, 0).toFixed(2)} TL</p>
                        </div>
                    </div>
                `;
            }

            document.getElementById('order-summary').innerHTML = orderSummaryHTML;
        })
        .catch(error => console.error('Error loading order details:', error));
});
