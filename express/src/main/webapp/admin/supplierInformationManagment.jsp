<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="tr">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Kullanıcı Bilgilerim</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/supplierInformationManagmenetStyle.css"/>
</head>
<%@ include file="../components/adminHeader/adminHeader.jsp" %>
<%@ include file="../components/footer/footer.jsp" %>
<body>
    <div class="container">
        <h1>Kullanıcı Bilgilerim</h1>
        <div class="card-container">
            <div class="card user-info">
                <h2>Üyelik Bilgilerim</h2>
                <form id="supplierForm">
                    <div class="form-group">
                        <label for="first_name">Ad</label>
                        <input type="text" id="first_name" name="first_name"/>
                    </div>
                    <div class="form-group">
                        <label for="last_name">Soyad</label>
                        <input type="text" id="last_name" name="last_name"/>
                    </div>
                    <div class="form-group">
                        <label for="shop_name">Mağaza Adı</label>
                        <input type="text" id="shop_name" name="shop_name"/>
                    </div>
                    <div class="form-group">
                        <label for="iban">IBAN</label>
                        <input type="text" id="iban" name="iban"/>
                    </div>
                    <div class="form-group">
                        <label for="email">E-Mail</label>
                        <input type="email" id="email" name="email"/>
                    </div>
                    <div class="form-group">
                        <label for="phone">Cep Telefonu</label>
                        <div class="phone-group">
                            <input type="text" id="phone" name="phone"/>
                        </div>
                    </div>
                    <button type="button" class="update-btn" onclick="updateUserInfo()">Güncelle</button>
                </form>
            </div>

            <div class="card">
                <h2>Şifre Güncelleme</h2>
                <form id="passwordForm">
                    <div class="form-group">
                        <label for="current_password">Şu Anki Şifre</label>
                        <input type="password" id="current_password" name="current_password"/>
                    </div>
                    <div class="form-group">
                        <label for="new_password">Yeni Şifre</label>
                        <input type="password" id="new_password" name="new_password"/>
                    </div>
                    <div class="form-group">
                        <label for="confirm_password">Yeni Şifre (Tekrar)</label>
                        <input type="password" id="confirm_password" name="confirm_password"/>
                    </div>
                    <button type="button" class="update-btn" onclick="updatePassword()">Güncelle</button>
                </form>
            </div>
            
            <div class="card">
                <h2>Hesap Kapatma</h2>
                <form id="deleteAccountForm">
                    <div class="form-group">
                        <label for="delete_email">E-Mail</label>
                        <input type="email" id="delete_email" name="delete_email"/>
                    </div>
                    <div class="form-group">
                        <label for="delete_password">Şifre</label>
                        <input type="password" id="delete_password" name="delete_password"/>
                    </div>
                    <button type="button" class="delete-btn" onclick="deleteAccount()">Hesabı Kapat</button>
                </form>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/supplierInformationManagmenetScript.js"></script>
</body>
</html>
