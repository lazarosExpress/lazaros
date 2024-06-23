<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="tr">

<head>
  <meta charset="utf-8">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Sipariş Ekranı</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/orderPageStyle.css" />
  <script src="${pageContext.request.contextPath}/js/orderPageScript.js"></script>
</head>

<%@ include file="../components/header/header.jsp" %>
<%@ include file="../components/footer/footer.jsp" %>

<body class="order-page">

  <main>
    <div class="basket">
      <div class="basket-module">
        <label for="promo-code">Promosyon Kodunuzu Giriniz</label>
        <input id="promo-code" type="text" name="promo-code" maxlength="5" class="promo-code-field">
        <button class="promo-code-cta">Apply</button>
      </div>
      <div class="basket-labels">
        <ul>
          <li class="item item-heading">Ürün</li>
          <li class="price">Tutar</li>
          <li class="quantity">Adet</li>
          <li class="subtotal">Toplam</li>
        </ul>
      </div>
      <div id="basket-products">
        <!-- Dinamik olarak doldurulacak -->
      </div>
    </div>
    <aside>
      <div class="summary">
        <div class="summary-total-items"><span class="total-items"></span>Teslimat ve Ödeme Bilgileri</div>
        <div class="summary-subtotal">
          <div class="subtotal-title">Toplam Tutar</div>
          <div class="subtotal-value final-value" id="basket-subtotal">0.00</div>
          <div class="summary-promo hide">
            <div class="promo-title">Promosyonn</div>
            <div class="promo-value final-value" id="basket-promo"></div>
          </div>
        </div>
        <div class="summary-delivery">
          <select name="delivery-collection" class="summary-delivery-selection">
            <option value="0" selected="selected">Teslimat Adresi Seçin</option>
          </select>
        </div>
        <div class="summary-payment">
          <select name="payment-collection" class="summary-payment-selection">
            <option value="0" selected="selected">Ödeme Yöntemi Seçiniz</option>
          </select>
        </div>
        <div class="summary-total">
          <div class="total-title">Toplam</div>
          <div class="total-value final-value" id="basket-total">0.00</div>
        </div>
        <div class="summary-checkout">
          <button class="checkout-cta">Siparişinizi Tamamlayın</button>
        </div>
      </div>
    </aside>
  </main>
</body>

</html>
