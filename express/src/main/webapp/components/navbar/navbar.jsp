<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbarStyle.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/navbarScript.js"></script>


<div class="example-megamenu" style="padding-top: 8px;">
  
  <nav class="navbar navbar-inverse">
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
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Today <span class="sr-only">(current)</span></a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-6 col-md-4">
                  <div class="media">
                    <div class="media-left">
                      <a class="link-image" href="#"><img class="media-object" src="https://via.placeholder.com/100"></a>
                      <a class="link-image link-image-sm" href="#"><img class="media-object" src="https://via.placeholder.com/46"></a>
                      <a class="link-image link-image-sm" href="#"><img class="media-object" src="https://via.placeholder.com/46"></a>
                    </div>
                    <div class="media-body">
                      <h5>Today's Featured Collections</h5>
                      <ul class="list-links">
                        <li><a href="#">Press Every Button</a></li>
                        <li><a href="#">Travel with Technology</a></li>
                        <li><a href="#">Smart Choice</a></li>
                        <li><a href="#">Fall in Love with Tech</a></li>
                        <li><a href="#">Smartphones at a Snip</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <div class="media">
                    <div class="media-left">
                      <a class="link-image" href="#"><img class="media-object" src="https://via.placeholder.com/100"></a>
                      <a class="link-image link-image-sm" href="#"><img class="media-object" src="https://via.placeholder.com/46"></a>
                      <a class="link-image link-image-sm" href="#"><img class="media-object" src="https://via.placeholder.com/46"></a>
                    </div>
                    <div class="media-body">
                      <h5>Today's Trending Collections</h5>
                      <ul class="list-links">
                        <li><a href="#">Harley-Davidson</a></li>
                        <li><a href="#">Will you be my Valentine?</a></li>
                        <li><a href="#">Summer Wedding Bridesmaid Dresses</a></li>
                        <li><a href="#">Pink Wedding Centerpiece Ideas</a></li>
                        <li><a href="#">Wedding Party Table Runners</a></li>
                        <li><a href="#">Backyard Wedding Reception</a></li>
                      </ul>
                    </div>
                  </div>
                </div>
                <div class="col-sm-6 col-md-4">
                  <h5>My Collections</h5>
                  <span class="text-muted">You currently have no collections. <a href="#">Learn how to create one</a>.</span>
                </div>
              </div>
            </div>
          </li>
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Fashion</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-4 col-md-2">
                  <h5>Top categories</h5>
                  <ul class="list-links">
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Men's</a></li>
                    <li><a href="#">Women's</a></li>
                    <li><a href="#">Kids</a></li>
                  </ul>
                </div>
                <div class="col-sm-4 col-md-3">
                  <h5>Shop for</h5>
                  <ul class="list-links">
                    <li role="separator" class="divider"></li>
                    <li><a href="#">Jewelry &amp; Watches</a></li>
                    <li><a href="#">Handbags &amp; Accessories</a></li>
                    <li><a href="#">Health &amp; Beauty</a></li>
                    <li><a href="#">Shoes</a></li>
                    <li><a href="#">Sales &amp; Events</a></li>
                  </ul>
                </div>
                <div class="col-sm-12 col-md-7">
                  <img src="https://via.placeholder.com/100x200" class="img-responsive" alt="Fashion">
                </div>
              </div>
            </div>
          </li>
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Electronics</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-4 col-md-2">
                  <h5>Top categories</h5>
                  <ul class="list-links">
                    <li><a href="#">Cell Phones &amp; Accessories</a></li>
                    <li><a href="#">Cameras &amp; Photo</a></li>
                    <li><a href="#">Computers &amp; Tablets</a></li>
                  </ul>
                </div>
                <div class="col-sm-4 col-md-3">
                  <h5>Other categories</h5>
                  <ul class="list-links">
                    <li><a href="#">Car Audio, Video &amp; GPS</a></li>
                    <li><a href="#">iPhone</a></li>
                    <li><a href="#">iPad</a></li>
                    <li><a href="#">TV, Audio</a></li>
                    <li><a href="#">Video Games &amp; Consoles</a></li>
                  </ul>
                </div>
                <div class="col-sm-12 col-md-7">
                  <img src="https://via.placeholder.com/100x200" class="img-responsive" alt="Electronics">
                </div>
              </div>
            </div>
          </li>
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Deals</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-3">
                  <h5>Best deals of the day</h5>
                  <ul class="list-links">
                    <li><a href="#">Car Audio, Video &amp; GPS</a></li>
                    <li><a href="#">iPhone</a></li>
                    <li><a href="#">iPad</a></li>
                    <li><a href="#">TV, Audio</a></li>
                    <li><a href="#">Video Games &amp; Consoles</a></li>
                  </ul>
                </div>
                <div class="col-sm-3">
                  <a href="#" class="thumbnail">
                    <img src="https://via.placeholder.com/100x140" alt="">
                    <div class="caption">
                      <h5>Waterproof cellphone cover</h5>
                      <p>$5.99</p>
                    </div>
                  </a>
                </div>
                <div class="col-sm-3">
                  <a href="#" class="thumbnail">
                    <img src="https://via.placeholder.com/100x140" alt="">
                    <div class="caption">
                      <h5>Large 20 slot leather watch box organizer</h5>
                      <p>$25.99</p>
                    </div>
                  </a>
                </div>
                <div class="col-sm-3">
                  <a href="#" class="thumbnail">
                    <img src="https://via.placeholder.com/100x140" alt="">
                    <div class="caption">
                      <h5>Samsung Galaxy Tab A SM-P550NZAAXAR 9.7-Inch W-Fi Tablet (Titanium with S-Pen)</h5>
                      <p>$319</p>
                    </div>
                  </a>
                </div>
              </div>
            </div>
          </li>
          <li class="dropdown dropdown-megamenu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Contact Us</a>
            <div class="dropdown-container">
              <div class="row">
                <div class="col-sm-6 col-md-4">
                  <h5>Contact us</h5>
                  <p>Feel free to drop us a line, we will respond as soon as possible.</p>
                  <form>
                    <div class="form-group">
                      <label class="sr-only" for="exampleInputEmail1">Email address</label>
                      <input type="email" class="form-control" id="exampleInputEmail1" placeholder="Your Email">
                    </div>
                    <div class="form-group">
                      <label class="sr-only" for="exampleInputText1">Text</label>
                      <textarea type="text" class="form-control" id="exampleInputText1" placeholder="Your Message" rows="3"></textarea>
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                  </form>
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