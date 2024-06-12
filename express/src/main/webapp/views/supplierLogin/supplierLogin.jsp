<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="tr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Lazaros'a Giriş yapın</title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Reddit+Mono:wght@200..900&display=swap" rel="stylesheet">

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css">
</head>
<body>

<div class="main">
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure><img src="${pageContext.request.contextPath}/images/supplie-signin-image.jpg" alt="sing up image"></figure>
                    <a href="supplierRegistration.jsp" class="signup-image-link">Yeni Satıcı Hesabı Oluştur</a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Satıcı Girişi</h2>
                    <form method="post" action="/express/SupplierController?action=LOGIN" class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="supplier_eMail"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="supplier_eMail" id="supplier_eMail" placeholder="e-Posta" required />
                            </div>
                            <div class="form-group">
                                <label for="supplier_password"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="supplier_password" id="supplier_password" placeholder="Şifre"    required />
                            </div>
                            <div class="form-group">
                                <input type="checkbox" name="rememberMe" id="rememberMe" class="checkbox" />
                                <label for="rememberMe" class="label-agree-term">
                                    <span><span></span></span>
                                    Beni hatırla
                                </label>
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="Giriş Yap" />
                                    <a href="${pageContext.request.contextPath}/views/login/login.jsp" class="form-submit">Müşteri Girişi</a>
                            </div>
                        </form>
                </div>
            </div>
        </div>
    </section>
</div>

<script src="vendor/jquery/jquery.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>
