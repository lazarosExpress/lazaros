<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Adres Bilgilerim</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addressStyle.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<%@ include file="../components/header/header.jsp" %>
<%@ include file="../components/footer/footer.jsp" %>
<body>
<div  class="address-style-css">
    <div class="container">
        <div class="address-header-card">
            <h1>Adres Bilgilerim</h1>
            <button class="add-button" onclick="showAddAddressModal()">+ Yeni Adres Ekle</button>
        </div>
        <div class="address-card-container" id="addresses">
            <table>
                <tbody id="addresses">
                    
                </tbody>
            </table>
        </div>
    </div>

    <!-- Add Address Modal -->
    <div id="addAddressModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeAddAddressModal()">&times;</span>
            <h2>Adres Ekle</h2>
            <form id="addAddressForm" action="${pageContext.request.contextPath}/AddressController?action=insert" method="post">
                <label for="address_customerFirstName">Ad*</label>
                <input type="text" id="address_customerFirstName" name="address_customerFirstName" placeholder="Adınızı Giriniz" required>
                
                <label for="address_customerLastName">Soyad*</label>
                <input type="text" id="address_customerLastName" name="address_customerLastName" placeholder="Soyadınızı Giriniz" required>

                <label for="address_customerPhoneNumber">Telefon*</label>
                <input type="tel" id="address_customerPhoneNumber" name="address_customerPhoneNumber" placeholder="0 (___) ___ __ __" required>
                
                <label for="province">İl:</label>
                <select id="province" name="province" onchange="loadDistricts(this.value)">
                    <option value="">Seçiniz</option>
                </select>

                <div id="district-container">
                    <label for="district">İlçe:</label>
                    <select id="district" name="district" onchange="loadSubDistricts(this.value)">
                        <option value="">Seçiniz</option>
                    </select>
                </div>

                <div id="subdistrict-container">
                    <label for="subdistrict">Semt:</label>
                    <select id="subdistrict" name="subdistrict" onchange="loadNeighbourhoods(this.value)">
                        <option value="">Seçiniz</option>
                    </select>
                </div>

                <div id="neighbourhood-container">
                    <label for="neighbourhood">Mahalle:</label>
                    <select id="neighbourhood" name="neighbourhood">
                        <option value="">Seçiniz</option>
                    </select>
                </div>

                <label for="address_description">Adres*</label>
                <textarea id="address_description" name="address_description" placeholder="Cadde, mahalle sokak ve diğer bilgileri giriniz." required></textarea>

                <label for="address_title">Adres Başlığı*</label>
                <input type="text" id="address_title" name="address_title" placeholder="Adres Başlığı Giriniz" required>

                <button type="submit">Kaydet</button>
            </form>
        </div>
    </div>
    <div id="editAddressModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeEditAddressModal()">&times;</span>
            <h2>Adresi Düzenle</h2>
            <form id="editAddressForm" action="${pageContext.request.contextPath}/AddressController?action=update" method="post">
                <input type="hidden" id="edit_address_id" name="address_id">
                <label for="edit_address_customerFirstName">Ad*</label>
                <input type="text" id="edit_address_customerFirstName" name="address_customerFirstName" placeholder="Adınızı Giriniz" required>
                
                <label for="edit_address_customerLastName">Soyad*</label>
                <input type="text" id="edit_address_customerLastName" name="address_customerLastName" placeholder="Soyadınızı Giriniz" required>

                <label for="edit_address_customerPhoneNumber">Telefon*</label>
                <input type="tel" id="edit_address_customerPhoneNumber" name="address_customerPhoneNumber" placeholder="0 (___) ___ __ __" required>
                
                <label for="edit_province">İl:</label>
                <select id="edit_province" name="province" onchange="loadEditDistricts(this.value)">
                    <option value="">Seçiniz</option>
                </select>

                <div id="edit_district-container">
                    <label for="edit_district">İlçe:</label>
                    <select id="edit_district" name="district" onchange="loadEditSubDistricts(this.value)">
                        <option value="">Seçiniz</option>
                    </select>
                </div>

                <div id="edit_subdistrict-container">
                    <label for="edit_subdistrict">Semt:</label>
                    <select id="edit_subdistrict" name="subdistrict" onchange="loadEditNeighbourhoods(this.value)">
                        <option value="">Seçiniz</option>
                    </select>
                </div>

                <div id="edit_neighbourhood-container">
                    <label for="edit_neighbourhood">Mahalle:</label>
                    <select id="edit_neighbourhood" name="neighbourhood">
                        <option value="">Seçiniz</option>
                    </select>
                </div>

                <label for="edit_address_description">Adres*</label>
                <textarea id="edit_address_description" name="address_description" placeholder="Cadde, mahalle sokak ve diğer bilgileri giriniz." required></textarea>

                <label for="edit_address_title">Adres Başlığı*</label>
                <input type="text" id="edit_address_title" name="address_title" placeholder="Adres Başlığı Giriniz" required>

                <button type="submit">Kaydet</button>
            </form>
        </div>
    </div>
</div>
    <script src="${pageContext.request.contextPath}/js/addressScript.js"></script>
</body>
</html>

