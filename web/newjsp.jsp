<%-- 
    Document   : content
    Created on : Oct 1, 2017, 1:34:56 AM
    Author     : kuminhdey
--%>

<%@page import="model.ProductDAO"%>
<%@page import="model.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% CategoryDAO cateDAO = new CategoryDAO();
    ProductDAO proDAO = new ProductDAO(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Content</title>
    </head>
    <body>
        <section>
            <div class="brand">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="box-title">Thương hiệu</h3>
                        </div>
                    </div>
                    <div class="row">
                        <%
                            int size = 3;
                            if (cateDAO.getAllDanhMuc().size() < 3) {
                                size = cateDAO.getAllDanhMuc().size();
                            }
                            for (int i = 0; i < size; i++) { %>
                        <div class="col-sm-4 text-center">
                            <a href="UserServlet?action=xemtheodanhmuc&iddanhmuc=<%= cateDAO.getAllDanhMuc().get(i).getCategoryid()%>"><img src="https://img.websosanh.vn/users/review/images/LcmFmY1BLbJj.jpg" alt="Son MAC" class="img-thumbnail zoom"></a>
                            <p><%= cateDAO.getAllDanhMuc().get(i).getCategoryname()%></p>
                        </div>
                        <% }%>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="product">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <h3 class="box-title">Sản phẩm</h3>
                        </div>
                    </div>
                    <div class="row">
                        <ul class="list-unstyled menu-product">
                             <% 
                                 int size1 = 9;
                                 if(proDAO.getAllProduct("").size() < 9){
                                     size1 = proDAO.getAllProduct("").size();
                                 }
                                 for(int i = 0; i < size1; i++){
                             %>
                            <div class="col-sm-4">
                                <li>
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <div class="ih-item square effect7">
                                                <a href="GioHangServlet?action=xem&idsanpham=<%= proDAO.getAllProduct("").get(i).getProductid()%>">
                                                    <div class="img"><img src="images/<%= proDAO.getAllProduct("").get(i).getProductimage() %>" alt="img" style="margin-top: 30px; "height="42" width="42"></div>
                                                    <div class="info">
                                                        <h3><%= proDAO.getAllProduct("").get(i).getProductname()%></h3>
                                                        <span style="color: red; font-weight: bold;">Giá: <%= proDAO.getAllProduct("").get(i).getProductprice()%> VNĐ </span> <br>
                                                        <span><%= proDAO.getAllProduct("").get(i).getProductdetail()%></span><br>
                                                    </div>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <h4><%= proDAO.getAllProduct("").get(i).getProductname()%></h4>
                                    <p><%= proDAO.getAllProduct("").get(i).getProductprice()%> VNĐ</p>
                                    <div class="icon">
                                        <a href="product-detail.html"><button type="button" class="btn btn-default">Mua</button></a>
                                        <a href="#"><button type="button" class="btn btn-default">Mua trả góp</button></a>
                                    </div>
                                </li>
                            </div>
                            <% } %>
                        </ul>
                        
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="news">
                <div class="container">
                    <div class="row">
                        <h3 class="text-left">News</h3>
                        <div class="col-sm-3">
                            <p class="time-info"><i class="glyphicon glyphicon-time"></i>07/04/2017</p>
                            <h4>TINH CHẤT NHAU THAI CỪU LÀM TRẮNG DA, TRỊ NÁM ROSANNA</h4>
                            <p class="news-info">Tinh chất nhau thai cừu làm trắng da, trị nám Rosanna được chiết xuất từ thành phần nhau thai cừu tự nhiên và được bào chế dưới dạng lỏng giúp dưỡng chất dễ dàng hấp thu vào da cho hiệu quả nhanh[…]</p>
                            <button type="button" class="btn btn-default btn-sm btn bt-color">Đọc thêm</button>  
                        </div>
                        <div class="col-sm-3">
                            <p class="time-info"><i class="glyphicon glyphicon-time"></i>07/04/2017</p>
                            <h4>SỮA RỬA MẶT TẨY TẾ BÀO CHẾT STIVES</h4>

                            <p></p>
                            <p></p>
                            <p></p><p></p>
                            <p class="news-info">Một làn da đẹp, một làn da trắng mịn đầy sức sống là niềm ao ước của các bạn gái chính vì thế hôm nay Dealhotvn giới thiệu đến bạn Sữa rửa mặt chuyên dành cho da bị mụn đầu đen và mụn trứng cá với các hạt Scrub  tẩy sạch mụn  đầu đen, trứng cá một cách […]</p>
                            <button type="button" class="btn btn-default btn-sm btn bt-color">Đọc thêm</button>
                        </div>
                        <div class="col-sm-3">
                            <p class="time-info"><i class="glyphicon glyphicon-time"></i>07/04/2017</p>
                            <h4>KEM NỀN CHE KHUYẾT ĐIỂM DERMACOL</h4>
                            <p class="news-info">Kem Nền Dermacol Không Thấm Nước Kem nền Dermacol đã trải qua rất nhiều cuộc thử nghiệm của các nhà khoa học về độ bền màu và độ thấm nước. Kem nền Dermacol có thể tồn tại trên da trong thời gian dài, ít bị phai màu và đặc biệt không thâm[…]</p>
                            <button type="button" class="btn btn-default btn-sm btn bt-color">Đọc thêm</button>
                        </div>
                        <div class="col-sm-3">
                            <p class="time-info"><i class="glyphicon glyphicon-time"></i>07/04/2017</p>
                            <h4>Không chỉ bán quần áo, giờ ASOS còn bán cả mỹ phẩm</h4>
                            <p class="news-info">Được biết đến là hệ thống mua sắm online lớn nhất thế giới, nhưng từ trước tới nay, ASOS vẫn chỉ bán quần áo, giày dép hay phụ kiện thông thường. Vậy mà mới đây, hãng lại khiến các tín đồ hoàn toàn bất ngờ khi quyết định tung ra ASOS Makeup[...]</p>
                            <button type="button" class="btn btn-default btn-sm btn bt-color">Đọc thêm</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
