<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>Lazaros'a Kaydolun...</title>

<link href="https://fonts.googleapis.com/css2?family=Reddit+Mono:wght@200..900&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css">

</head>
<body>

<div class="main">

    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Kayıt Ol</h2>
                    <form method="post" action="/express/CustomerController?action=CREATE" class="register-form" id="register-form">
                        <div class="form-group">
                            <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="customer_firstName" id="customer_firstName" placeholder="İsim" required />
                        </div>
                        <div class="form-group">
                            <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                            <input type="text" name="customer_lastName" id="customer_lastName" placeholder="Soyisim" required />
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="zmdi zmdi-email"></i></label>
                            <input type="email" name="customer_eMail" id="customer_eMail" placeholder="e-Posta" required />
                        </div>
                        <div class="form-group">
                            <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                            <input type="password" name="customer_password" id="customer_password" placeholder="Şifre" required />
                        </div>
                        <div class="form-group">
                            <label for="contact"><i class="zmdi zmdi-phone"></i></label>
                            <input type="text" name="customer_number" id="customer_number" placeholder="Telefon numarası" required />
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" required />
                            <label for="agree-term" class="label-agree-term">
                                <span><span></span></span>
                                Tüm kullanım şartlarını kabul ediyorum <a href="#" class="term-service">Kullanım Şartları</a>
                            </label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup" class="form-submit" value="Kaydol" />
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure><img src="${pageContext.request.contextPath}/images/signup-image.jpg" alt="sing up image"></figure>
                    <a href="login.jsp" class="signup-image-link">Giriş yap</a>
                </div>
            </div>
        </div>
    </section>

</div>
</body>
</html>
