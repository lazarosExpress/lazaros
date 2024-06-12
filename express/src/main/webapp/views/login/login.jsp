<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="tr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Lazaros'a Giriş yapın</title>

<link href="https://fonts.googleapis.com/css2?family=Reddit+Mono:wght@200..900&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css">

</head>
<body>

<div class="main">
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure><img src="${pageContext.request.contextPath}/images/signin-image.jpg" alt="sing up image"></figure>
                    <a href="registration.jsp" class="signup-image-link">Yeni Hesap Oluştur</a>
                </div>

                <div class="signin-form">
                    <h2 class="form-title">Müşteri Girişi</h2>
                    <form method="post" action="/express/CustomerController?action=LOGIN" class="register-form" id="login-form">
                            <div class="form-group">
                                <label for="customer_eMail"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="customer_eMail" id="customer_eMail" placeholder="e-Posta" required />
                            </div>
                            <div class="form-group">
                                <label for="customer_password"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="customer_password" id="customer_password" placeholder="Şifre"    required />
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
                                <a href="${pageContext.request.contextPath}/views/supplierLogin/supplierLogin.jsp" class="form-submit">Satıcı Girişi</a>
                            </div>
                        </form>
                </div>
            </div>
        </div>
    </section>

</div>


</body>
</html>
