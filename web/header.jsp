<%-- 
    Document   : header
    Created on : Oct 1, 2017, 1:32:56 AM
    Author     : kuminhdey
--%>

<%@page import="model.ProductDAO"%>
<%@page import="java.util.Vector"%>
<%@page import="model.CategoryDAO"%>
<%@page import="model.GioHangDAO"%>
<%@page import="model.CartDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% CategoryDAO cateDAO = new CategoryDAO(); %>
<% ProductDAO proDAO = new ProductDAO(); %>
<% CartDAO cartDAO = new CartDAO(); %>
<% GioHangDAO gioDAO = new GioHangDAO(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>

    </head>
    <body> 
        <header>
            <div class="top-bar navbar-fixed-top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="pull-left">
                                <div class="lang">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default dropdown-toggle no-boder" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color: #d23091;">
                                            Vietnamese <span class="caret"></span>
                                        </button>
                                        <ul class="dropdown-menu no-boder">
                                            <li><a href="#" style="color: #d23091;">Vietnamese</a></li>
                                            <li><a href="#" style="color: #d23091;">English</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="pull-right">
                                    <ul class="list-unstyled top-links">
                                        <div class="col-sm-4" style="
                                             padding-right: 0px;
                                             " ${IDNGUOIDUNG == null ? 'hidden="true"' : ''}>
                                            <div class="dropdown1">
                                                <strong >${USERNAME == null ? 0 : SOLUONG} Sản Phẩm <i class="glyphicon glyphicon-shopping-cart"></i></strong>

                                                <div class="dropdown-content">
                                                    <table class="table" style="font-size: 13px; margin-bottom: 0;width: 700px;background-color: white;">
                                                        <tbody>
                                                            <% if (session.getAttribute("USERNAME") != null) {
                                                                    for (int i = 0; i < cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).size(); i++) {
                                                                        request.setAttribute("IDSANPHAM", cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getProductid());
                                                            %>
                                                            <tr>
                                                                <td>
                                                                    <a style="text-decoration: none;" href="GioHangServlet?action=xem&idsanpham=<%= cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getProductid()%>">
                                                                    <img style="height: 80px; width: 80px;float: left; padding-right: 10px;"src="images/<%= cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getProductImage()%>"> <%=cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getProductName()%>
                                                                    </a>
                                                                    </td>
                                                                <td ><%= cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getDonGia()%> VNĐ</td>
                                                                <td>
                                                        <center>
                                                            <table>
                                                                <tbody>
                                                                    <tr>
                                                                        <td><center><%= cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getSoLuong()%></center></td>
                                                                <a style="padding: 0;" href="GioHangServlet?action=tang&idsanpham=<%= cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getProductid()%>&idnguoidung=${IDNGUOIDUNG != null ? IDNGUOIDUNG : '0'}"><i class="fa fa-chevron-up" aria-hidden="true"></i></a>

                                                                </tr>
                                                                <tr> 
                                                                    <td><a style="padding: 0;" href="GioHangServlet?action=giam&idsanpham=<%= cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getProductid()%>&idnguoidung=${IDNGUOIDUNG != null ? IDNGUOIDUNG : '0'}"><i class="fa fa-chevron-down" aria-hidden="true"></i></a></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </center>
                                                        </td>
                                                        <td class="price"><%= cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getTongGia()%> VNĐ</td>
                                                        <% request.setAttribute("IDSANPHAM", cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getProductid()); %>
                                                        <td><center><a style="padding: 0;" href="GioHangServlet?action=del&idnguoidung=${IDNGUOIDUNG}&idsanpham=${IDSANPHAM}" style="text-decoration:none;display: block;width: 100px;"><i class="fa fa-trash" aria-hidden="true"></i></a></center></td>
                                                        </tr>
                                                        <% }
                                                            } %>
                                                        <tr>    
                                                            <td style="color: #d23091; font-weight: bold;">TỔNG CỘNG:  <%
                                                                double tong = 0;
                                                                for (int i = 0; i < cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).size(); i++) {
                                                                    tong = tong + cartDAO.getAllCart((String) session.getAttribute("IDNGUOIDUNG")).get(i).getTongGia();
                                                                }
                                                                %>
                                                                <%= tong%> VNĐ</td>
                                                            <td>

                                                            </td> 
                                                            <td></td>
                                                            <td><a href="cart.jsp"><button style="display: inline-block;
                                                                                           padding: 2px 20px;
                                                                                           margin-bottom: 0;
                                                                                           font-size: 15px;
                                                                                           color: white;
                                                                                           background-color: #d23091;
                                                                                           font-weight: 400;
                                                                                           line-height: 1.42857143;
                                                                                           text-align: center;
                                                                                           white-space: nowrap;
                                                                                           vertical-align: middle;
                                                                                           touch-action: manipulation;
                                                                                           cursor: pointer;
                                                                                           user-select: none;
                                                                                           background-image: none;
                                                                                           border: 1px solid transparent;
                                                                                           border-radius: 4px;">Thanh Toán</button></a></td>
                                                            <td></td>

                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>

                                        </div>
                                                            
                                <li><a href="#" id="minh" onclick="document.getElementById('id01').style.display = 'block'" style="width:auto;"> ${USERNAME != null ? '' : 'Đăng nhập'} </a></li>
                                <li><a href="#" onclick="document.getElementById('id02').style.display = 'block'">${USERNAME != null ? '' : 'Đăng ký'}</a></li>
                                <li><a href="#">${USERNAME}</a></li>
                                    ${USERNAME != null ? '<li><a href="cart.jsp">Giỏ Hàng</a></li> ' : ''} 
                                    ${USERNAME != null ? '<li><a href="UserServlet?action=logout">Đăng Xuất</a></li> ' : ''} 
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="id01" class="modal login-form"> 
                <form class="modal-content animate" action="UserServlet" method="post">
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    </div>
                    <h3 class="text-center">ĐĂNG NHẬP</h3>
                    <div class="login" >
                        <label><b>Tên đăng nhập</b></label>
                        <input type="text" placeholder="Nhập tài khoản"  required name="username">

                        <label><b>Mật khẩu</b></label>
                        <input type="password" placeholder="Nhập mật khẩu" required name="pass">

                        <button type="submit" name="action" value="login" >Đăng nhập</button>
                        <input type="checkbox" checked="checked"> Nhớ tài khoản
                    </div>

                    <div style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">Cancel</button>
                        <span class="psw"><a href="#">Quên mật khẩu?</a></span>
                    </div>
                </form>
            </div>
            <div id="id02" class="modal sign-form"> 
                <form class="modal-content animate" action="UserServlet" method="POST">
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('id02').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    </div>
                    <h3 class="text-center">ĐĂNG KÝ</h3>
                    <div class="sign">
                        <label><b>Tên đăng nhập</b></label>
                        <input type="text" placeholder="Nhập tên đăng nhập"  required name="username">

                        <label><b>Email</b></label>
                        <input type="email" placeholder="Nhập email"  required name="email">

                        <label><b>Mật khẩu</b></label>
                        <input type="password" placeholder="Nhập mật khẩu" required name="pass">

                        <label><b>Nhập lại mật khẩu</b></label>
                        <input type="password" placeholder="Nhập lại mật khẩu" required>

                        <input type="checkbox" checked="checked"> Nhớ tài khoản
                        <p>Bằng việc click vào các nút Đăng ký bạn đã đồng ý <a href="#">Điều khoản sử dụng</a>.</p>
                        <div class="clearfix">
                            <button type="submit" onclick="document.getElementById('id02').style.display = 'none'" style="background-color: #f44336;">Cancel</button>
                            <button type="submit" name="action" value="add">Đăng Ký</button>
                        </div>            
                    </div>
                </form>
            </div>
            <div class="main-header">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="logo text-center" >
                                <a href="index.jsp"><img src="images/logo.png" style="width:400px;height: 80px;margin-left: -100px;"></a>
                            </div>
                        </div>
                        <div class="col-sm-3 search-w">
                            <div class="col-lg-6">
                                <form action="UserServlet" method="get">
                                    <div class="input-group">
                                        <input type="text" name="keyword" class="form-control" placeholder="Search for...">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" name="action" value="search"><i class="glyphicon glyphicon-search" style="width: 14px; height: 20.2px;"></i></button>
                                        </span>
                                    </div>
                                </form>

                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <div class="main">
                <div class="container">
                    <div class="row">
                        <div class="main-nav">
                            <div class="container">
                                <div class="row" >
                                    <ul class="nav nav-pills">
                                        <li class="active"><a href="index.jsp"><i class="glyphicon glyphicon-home"></i> Trang chủ<span class="sr-only">(current)</span></a></li>
                                        <li><a href="#">Giới thiệu</a></li>
                                        <li><a href="sanpham.jsp">Sản phẩm</a></li>
                                        <li class="dropdown"><a>Thương hiệu</a>
                                            <ul class="dropdown-menu">
                                                <%
                                                    for (int i = 0; i < cateDAO.getAllDanhMuc().size(); i++) {
                                                %>
                                                <li><a href="UserServlet?action=xemtheodanhmuc&iddanhmuc=<%= cateDAO.getAllDanhMuc().get(i).getCategoryid()%>"><%= cateDAO.getAllDanhMuc().get(i).getCategoryname()%></a></li>
                                                    <% }%>
                                            </ul>
                                        </li>
                                        <li><a href="#lienhe">Liên hệ</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    </body>
</html>
