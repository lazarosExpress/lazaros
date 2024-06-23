<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="tr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Müşteri Yönetimi</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/updateCustomerManagementStyle.css" />
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="${pageContext.request.contextPath}/js/updateCustomerManagementScript.js" defer></script>
</head>

<body>
    <%@ include file="../components/adminHeader/adminHeader.jsp" %>
    <div class="container">
        <div class="section">
            <h2 class="page-title">Müşteri Yönetimi</h2>
            <div class="card">
                <h3>Müşteri Düzenle</h3>
                <form id="customerForm" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="customer_id" id="customer_id" />
                    <div class="form-group">
                        <label for="customer_firstName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="text" name="customer_firstName" id="customer_firstName" placeholder="İsim" required />
                    </div>
                    <div class="form-group">
                        <label for="customer_lastName"><i class="zmdi zmdi-account material-icons-name"></i></label>
                        <input type="text" name="customer_lastName" id="customer_lastName" placeholder="Soyisim" required />
                    </div>
                    <div class="form-group">
                        <label for="customer_eMail"><i class="zmdi zmdi-email"></i></label>
                        <input type="email" name="customer_eMail" id="customer_eMail" placeholder="e-Posta" required />
                    </div>
                    <div class="form-group">
                        <label for="customer_phoneNumber"><i class="zmdi zmdi-phone"></i></label>
                        <input type="text" name="customer_phoneNumber" id="customer_phoneNumber" placeholder="Telefon numarası" required />
                    </div>
                    <button type="button" class="btn btn-primary" onclick="updateCustomer()">Güncelle</button>
                </form>
            </div>
        </div>
    </div>    
    <%@ include file="../components/footer/footer.jsp" %>
</body>

</html>
