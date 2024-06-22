<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbarStyle.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/navbarScript.js"></script>

<div class="example-megamenu" style="padding-top: 8px;">
  <nav class="navbar navbar-inverse" style="height: 52px;">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${pageContext.request.contextPath}"><strong>Lazaros</strong></a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          
          <li class="dropdown dropdown-megamenu open">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Moda <span class="sr-only">(current)</span></a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-6 col-md-4">
                  <div class="media">
                    <div class="media-left">
                      <a class="link-image" href="#">    
                        <img class="media-object" src="https://sinsirella.net/wp-content/uploads/2021/10/en-iyi-kadin-giyim-markalari.jpg" alt="Example Image" style="width: 120px; height: 120px; object-fit:cover;">
                      </a>
                      <a class="link-image link-image-sm" href="#">
                        <img class="media-object"src="https://fns.modanisa.com/r/pro2/2023/10/19/z-dugmesiz-kase-astarli-kadin-kaban-antrasit-8883249-1.JPG" alt="Example Image" style="width: 55px; height: 55px; object-fit: cover;">
                      </a>
                      <a class="link-image link-image-sm" href="#">
                        <img class="media-object" src="https://static.ticimax.cloud/cdn-cgi/image/width=-,quality=85/6799/uploads/urunresimleri/buyuk/stiletto-yuksek-topuklu-siyah-suet-kad-ad6b-8.jpg" alt="Example Image" style="width: 55px; height: 55px; object-fit: cover;">
                      </a>
                    </div>
                    <div class="media-body">
                      <h5>Kadın Giyim</h5>
                      <ul class="list-links">
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(12);">Elbise</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(13);">Triko & Kazak</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(14);">Mont Kaban</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(15);">Sweatshirt</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(16);">Pantolon</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(17);">Ayakkabı</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <div class="media">
                    <div class="media-left">
                      
                      <a class="link-image" href="#"><img class="media-object"src="https://static.ticimax.cloud/cdn-cgi/image/width=540,quality=99/38108/uploads/sayfatasarim/sayfa14/erkek-giyim-modelleri-ddd1.jpg" alt="Example Image" style="width: 120px; height: 120px; object-fit: cover;"></a>
                      <a class="link-image link-image-sm" href="#"><img class="media-object" src="https://static.ticimax.cloud/5334/uploads/urunresimleri/buyuk/waffle-kumas-haki-basic-erkek-pantolon-3127f8.jpeg" alt="Example Image" style="width: 55px; height: 55px; object-fit: cover;"></a>
                      <a class="link-image link-image-sm" href="#"><img class="media-object" src="https://static.ticimax.cloud/12079/uploads/urunresimleri/buyuk/kahverengi-klasik-erkek-ayakkabi-552-0-f06-0e.jpg" alt="Example Image" style="width: 55px; height: 55px; object-fit: cover;"></a>
                    </div>
                    <div class="media-body">
                      <h5>Erkek Giyim</h5>
                      <ul class="list-links">
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(18);">T-shirt</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(19);">Sweatshirt</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(20);">Gömlek</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(21);">Eşofman</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(22);">Pantolon</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(23);">Mont & Kaban</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(24);">Ayakkabı</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <div class="media">
                    <div class="media-left">
                      <a class="link-image" href="#"><img class="media-object"src="https://img-lcwaikiki.mncdn.com/mnresize/1020/1360/pim/productimages/20232/6938984/v1/l_20232-w3ie10z4-lqj-127-27_5.jpg" alt="Example Image" style="width: 100px; height: 120px; object-fit: cover;"></a>
                      <a class="link-image link-image-sm" href="#"><img class="media-object" src="https://img.slazenger.com.tr/slazenger-phyllida-kiz-cocuk-tayt-lila-tayt-211851-11-B.jpg" alt="Example Image" style="width: 46px; height: 55px; object-fit: cover;"></a>
                      <a class="link-image link-image-sm" href="#"><img class="media-object" src="https://static.ticimax.cloud/cdn-cgi/image/width=-,quality=85/51093/uploads/urunresimleri/buyuk/beyaz-fiyonklu-simli-kiz-cocuk-ayakkab-164abb.jpg" alt="Example Image" style="width: 46px; height: 55px; object-fit: cover;"></a>
                    </div>
                    <div class="media-body">
                      <h5>Kız Çocuk Giyim</h5>
                      <ul class="list-links">
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(25);">Elbise</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(26);">T-shirt</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(27);">Sweatshirt</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(28);">Tayt</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(29);">Eşofman</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(30);">Pantolon</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(31);">Ayakkabı</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <div class="media">
                    <div class="media-left">
                      <a class="link-image" href="#">    
                        <img class="media-object" src="https://floimages.mncdn.com/media/catalog/product/24-06/03/201162862-1-1717412900.jpg" alt="Example Image" style="width: 120px; height: 120px; object-fit:cover;">
                      </a>
                      <a class="link-image link-image-sm" href="#">
                        <img class="media-object"src="https://fns.modanisa.com/r/pro2/2023/10/19/z-dugmesiz-kase-astarli-kadin-kaban-antrasit-8883249-1.JPG" alt="Example Image" style="width: 55px; height: 55px; object-fit: cover;">
                      </a>
                      <a class="link-image link-image-sm" href="#">
                        <img class="media-object" src="https://static.ticimax.cloud/cdn-cgi/image/width=-,quality=85/6799/uploads/urunresimleri/buyuk/stiletto-yuksek-topuklu-siyah-suet-kad-ad6b-8.jpg" alt="Example Image" style="width: 55px; height: 55px; object-fit: cover;"></a>
                    </div>
                    <div class="media-body">
                      <h5>Erkek Çocuk Giyim</h5>
                      <ul class="list-links">
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(32);">T-shirt</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(33);">Sweatshirt</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(34);">Gömlek</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(35);">Eşofman</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(36);">Pantolon</a></li>
                        <li><a href="javascript:void(0);" onclick="getCategoryProductList(37);">Ayakkabı</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </li>
          
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Elektronik</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-3 col-md-2">
                  <h5>Bilgisayar/Tablet</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(38);">Dizüstü Bilgisayar</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(39);">Masaüstü Bilgisayar</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(40);">Tablet</a></li>
                  </ul>
                </div>
                <div class="col-sm-3 col-md-2">
                  <img src="https://assets.mmsrg.com/isr/166325/c1/-/ASSET_MMS_136037940/mobile_220_310_png/HP-Victus--i5-14500HX-16GB-RAM-512-GB-SSD-RTX4050-16.1%27%27--Win-11-Laptop-Siyah-9J259EA" class="img-responsive" alt="Electronics" style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
                <div class="col-sm-3 col-md-2">
                  <h5>Telefon&amp;Telefon Aksesuarları</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(41);">Cep Telefonu</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(42);">Akıllı Saatler</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(43);">Aksesuarlar</a></li>
                  </ul>
                </div>
                <div class="col-sm-3 col-md-2">
                  <img src="https://png.pngtree.com/png-clipart/20240403/original/pngtree-apple-iphone-11-pro-max-clear-case-3d-render-png-image_14745655.png" class="img-responsive" alt="Electronics" style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
                <div class="col-sm-3 col-md-2">
                  <h5>Foto&amp;Kamera</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(44);">Dijital Fotoğraf </a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(45);">Video Kameralar</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(46);">Aksiyon Kamerası</a></li>
                  </ul>
                </div>
                <div class="col-sm-3 col-md-2">
                  <img src="https://www.syd.com.tr/class/INNOVAEditor/assets/Smatree/Smatree%20Aksiyon%20Kamera%20DSLR%20Ba%C4%9Flat%C4%B1s%C4%B1%20(4).png" class="img-responsive" alt="Electronics" style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
                <div class="col-sm-3 col-md-2">
                  <h5>Beyaz Eşya</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(47);">Buzdolabı</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(48);">Bulaşık Makinası</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(49);">Çamaşır Makinası</a></li>
                  </ul>
                </div>
                <div class="col-sm-3 col-md-2">
                  <img src="https://www.arcelik.com.tr/media/resize/7282120192_LO1_20200409_154345.png/2000Wx2000H/image.webp" class="img-responsive" alt="Electronics" style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
                <div class="col-sm-3 col-md-2">
                  <h5>Oyuncu Ekipmanları</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(50);">Kulaklık</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(51);">Klavye</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(52);">Mouse</a></li>
                  </ul>
                </div>
                <div class="col-sm-3 col-md-2">
                  <img src="https://www.monsternotebook.com.tr/UPLOAD/urun-gorselleri-yeni/PUSAT/7.1lite-rework/thumb/1_medium.png" class="img-responsive" alt="Electronics" style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
              </div>
            </div>
          </li>
          
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Spor&Outdoor</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-3 col-md-2">
                  <h5>Spor&Outdoor</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(53);">Spor Ayakkabı</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(54);">Fitness&Kondisyon</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(55);">Kampçılık Ekipmanları</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(56);">Şişme Su Ürünleri</a></li>
                  </ul>
                </div>
                <div class="col-sm-3 col-md-4">
                  <img src="https://img.freepik.com/free-photo/equipment-weightlifting-health-fitness-power_1368-2533.jpg" class="img-responsive" alt="spor" style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
              </div>
            </div>
          </li>
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Anne, Bebek, Oyuncak</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-3 col-md-2">
                  <h5>Anne&Bebek</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(57);">Bebek Giyim</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(58);">Bebek Maması</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(59);">Bebek Bezi</a></li>
                  </ul>
                </div>
                <div class="col-sm-3 col-md-2">
                  <img src="https://png.pngtree.com/png-vector/20240212/ourlarge/pngtree-mother-and-child-pic-png-image_11740416.png" class="img-responsive" alt="anne-bebek"style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
                <div class="col-sm-3 col-md-2">
                  <h5>Oyuncak</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(60);">Bebek Oyuncakları</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(61);">Kız Çocuk Oyuncakları</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(62);">Erkek Çocuk Oyuncakları</a></li>
                  </ul>
                </div>
                <div class="col-sm-3 col-md-2">
                  <img src="https://img.lovepik.com/free-png/20211206/lovepik-toy-bear-png-image_401360896_wh1200.png" class="img-responsive" alt="Oyuncak"style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
              </div>
            </div>
          </li>
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Kozmetik, Kişisel Bakım</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-4 col-md-3">
                  <h5>Parfüm</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(63);">Erkek Parfüm</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(64);">Kadın Parfüm</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(65);">Lüks Parfüm</a></li>
                  </ul>
                </div>
                <div class="col-sm-4 col-md-3">
                  <h5>Makyaj</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(66);">Maskara</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(67);">Fondöten</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(68);">Eyeliner</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(69);">Ruj</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(70);">Kapatıcı</a></li>
                  </ul>
                </div>
                <div class="col-sm-4 col-md-3">
                  <h5>Bakım</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(71);">Cild Bakımı</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(72);">Ağız Bakımı</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(73);">Saç Bakımı</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(74);">Epilasyon&Ağda</a></li>
                  </ul>
                </div>
                <div class="col-sm-4 col-md-3">
                  <img src="https://static.wixstatic.com/media/22565c_dfe60b079dee457e90be5948a5b11a10~mv2.png/v1/fill/w_547,h_1000,al_c,q_90,enc_auto/22565c_dfe60b079dee457e90be5948a5b11a10~mv2.png" class="img-responsive" alt="Kozmetik"style="width: 100px; height: 140px; object-fit: cover; margin-bottom: 14px;">
                </div>
              </div>
            </div>
          </li>  
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Ev,Mobilya,Kırtasiye</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-4 col-md-3">
                  <h5>Ev Tekstili</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(75);">Nevresim</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(76);">Yorgan</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(77);">Yastık</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(78);">Halı&Kilim</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(79);">Perde</a></li>
                  </ul>
                </div>
                <div class="col-sm-4 col-md-3">
                  <h5>Mobilya</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(80);">Koltuk Takımları</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(81);">Masa Sandalye Takımları</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(82);">Dolap Takımları</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(83);">Yatak Odası Takımları</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(84);">Çalışma Masası</a></li>
                  </ul>
                </div>
                <div class="col-sm-4 col-md-3">
                  <h5>Kırtasiye</h5>
                  <ul class="list-links">
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(85);">Kitap&Defter</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(86);">Kalem&Boyama Kalemleri</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(87);">Ofis Sarf Malzemeleri</a></li>
                    <li><a href="javascript:void(0);" onclick="getCategoryProductList(88);">Fotokobi Kağıtları</a></li>
                  </ul>
                </div>
                <div class="col-sm-4 col-md-3">
                  <img src="https://e7.pngegg.com/pngimages/817/403/png-clipart-home-furnishings-home-furnishings-household-thumbnail.png" class="img-responsive" alt="Ev Mobilya Kırtasiye" style="width: 150px; height: 150px; object-fit: cover; margin-bottom: 14px;">
                </div>
              </div>
            </div>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Bize Ulaşın</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-6 col-md-4">
                  <h5 style="color:coral;">Bize Ulaşın</h5>
                  <ul class="contact-list">
                    <li><i class="fa-brands fa-instagram" style="color: orange;"></i> Instagram: <a href="#">your_instagram_handle</a></li>
                    <li><i class="fa-regular fa-envelope" style="color: orange;"></i> Email: <a href="#">your-email@example.com</a></li>
                    <li><i class="fa-solid fa-phone" style="color: orange;"></i> Phone Numbers:
                      <ul>
                        <li>+1234567890</li>
                        <li>+095369245308</li>
                        <li>+1122334455</li>
                      </ul>
                    </li>
                  </ul>
                </div>
                <div class="col-sm-6 col-md-8">
                  <div class="ratio ratio-16x9">
                    <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2994.593675177369!2d36.177023676438786!3d41.36118469761945!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40887f005ed7eb6f%3A0xeac58a07a907878b!2sSamsun%20Teknopark!5e0!3m2!1str!2str!4v1717947510339!5m2!1str!2str" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                  </div>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</div>

<script>
    function getCategoryProductList(categoryId) {
        window.location.href = `<%= request.getContextPath() %>/views/categoryProductList.jsp?categoryId=` + categoryId;
    }
</script>
