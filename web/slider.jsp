<%-- 
    Document   : slider
    Created on : Oct 1, 2017, 1:34:36 AM
    Author     : kuminhdey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Slider</title>
    </head>
    <body>
        <section class="slider">
            <div class="container">
                <div class="row">
                    <div class="slideshow">
                        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                            </ol>
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <img src="images/Slide5.jpg" alt="Mobile Shop" width="1170px" style="
                                         height: 400px;">
                                    <div class="carousel-caption">
                                        <h4>Tặng Quà Khi Mua Trên 3 Sản Phẩm</h4>
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="images/Slide2.gif" alt="Mobile Shop" width="1170px" style="
                                         height: 400px;">
                                    <div class="carousel-caption">
                                        <h4>Đặt Hàng Ngay Hôm Nay</h4>
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="images/Slide1.gif" alt="Mobile Shop" width="1170px" style="
                                         height: 400px;">
                                    <div class="carousel-caption">
                                        <h4>Ngày 20/10 Giảm Hơn 14%</h4>
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="images/Slide3.jpg" alt="Mobile Shop" width="1170px" style="
                                         height: 400px;">
                                    <div class="carousel-caption">
                                        <h4>Nhiều Mẫu Mã Cho Các Bạn Lựa Chọn</h4>
                                    </div>
                                </div>
                                <div class="item">
                                    <img src="images/Slide4.jpg" alt="Mobile Shop" width="1170px" style="
                                         height: 400px;">
                                    <div class="carousel-caption">
                                        <h4>Son MAC giảm giá 20%</h4>
                                    </div>
                                </div>
                            </div>
                            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
