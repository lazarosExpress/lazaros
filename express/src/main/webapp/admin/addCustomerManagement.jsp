<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="tr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Add Customer Management</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addCustomerManagementStyle.css" />
    
    <script src="${pageContext.request.contextPath}/js/addCustomerManagementScript.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>
    <%@ include file="../components/adminHeader/adminHeader.jsp" %>
    <div class="container">
        <div class="section">
            <h2 class="page-title">Müşteri Yönetimi</h2>
            <div class="card">
                <h3>Müşteri Ekle</h3>
                <form method="post" action="/express/CustomerController?action=CREATEWITHSUPPLIER" class="register-form" id="register-form">
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
                    <div class="form-group form-button">
                        <input type="submit" name="signup" id="signup" class="form-submit" value="Kaydet" />
                    </div>
                </form>
            </div>
        </div>
    </div>    
    <%@ include file="../components/footer/footer.jsp" %>
</body>

</html>
