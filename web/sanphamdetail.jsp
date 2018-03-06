<%-- 
    Document   : index
    Created on : Oct 1, 2017, 1:34:50 AM
    Author     : kuminhdey
--%>

<%@page import="model.ProductDAO"%>
<%@page import="model.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% CategoryDAO cateDAO = new CategoryDAO(); %>
<% ProductDAO proDAO = new ProductDAO(); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta charset="utf-8">
        <title>Sản Phẩm</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/ihover.css">
        <script type="text/javascript" src="js/javascript.js"></script>
        <link rel="stylesheet" href="dist/css/scrollToTop.css">
        <link rel="stylesheet" href="dist/css/easing.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%
             session.setAttribute("PAGECART", "sanphamdetail.jsp");
            if (request.getAttribute("THEMGIO") == "FAIL") {
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Vui Lòng Đăng Nhập');");
                out.println("location='sanphamdetail.jsp';");
                out.println("</script>");
            }
        %>
       <jsp:include page="/header.jsp"></jsp:include>
        <jsp:include page="/slider.jsp"></jsp:include>
            <section class="product-p">
                <div class="container">
                    <div class="col-sm-12">

                    </div>
                    <div class="col-sm-4 pull-left">
                        <div class="col-sm-12">
                            <h3 class="box-title">Nhãn hiệu</h3>
                            <ul>
                            <%
                                int size = 5;
                                if (cateDAO.getAllDanhMuc().size() < 5) {
                                    size = cateDAO.getAllDanhMuc().size();
                                }
                                for (int i = 0; i < size; i++) {
                            %>
                            <li><a href="UserServlet?action=xemtheodanhmuc&iddanhmuc=<%= cateDAO.getAllDanhMuc().get(i).getCategoryid()%>"><%= cateDAO.getAllDanhMuc().get(i).getCategoryname()%></a> <i class="glyphicon glyphicon-ok"></i></li>
                                <% }%>
                        </ul>
                    </div>
                    <div class="col-sm-12">
                        <h3 class="box-title">Sản Phẩm Gợi Ý</h3>
                        <ul>
                             <%
                                 size = 10;
                                if (proDAO.getAllProduct("").size() < 10) {
                                    size = proDAO.getAllProduct("").size();
                                }
                                for (int i = 0; i < size; i++) {
                            %>
                            <li><a href="GioHangServlet?action=xem&idsanpham=<%= proDAO.getAllProduct("").get(i).getProductid()%>"><%= proDAO.getAllProduct("").get(i).getProductname() %></a> <i class="glyphicon glyphicon-ok"></i></li>
                            <% }%>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-8 pull-right">
                    <h5><a href="index.jsp">Trang Chủ</a> >> <a href="sanpham.jsp" >Sản Phẩm</a> >> <a href="UserServlet?action=xemtheodanhmuc&iddanhmuc=${SANPHAM.category.categoryid}">${SANPHAM.category.categoryname}</a></h5>
                    <h2 style="color: #FF8F00;margin-top: 0px;">${SANPHAM.productname}</h2>
                    <div class="col-sm-6 pull-left img-p">
                        <img src="images/${SANPHAM.productimage}" class="img-ip">

                    </div>
                    <div class="col-sm-6 pull-right info-p">
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                        <br>
                        <h3 style="color: red;">Giá: ${SANPHAM.productprice} VNĐ</h3>
                        <a href="GioHangServlet?action=add&idsanpham=${SANPHAM.productid}&idnguoidung=${IDNGUOIDUNG != null ? IDNGUOIDUNG : '0'}&page=detail"><button type="button" class="btn btn-primary">Thêm vào giỏ</button></a> <br>
                        <button type="button" class="btn btn-warning">${USERNAME == null ? 0 : SOLUONG} Sản Phẩm <i class="glyphicon glyphicon-shopping-cart"></i></button>
                    </div>
                    <div class="col-sm-12">
                        <h3>THÔNG TIN SẢN PHẨM</h3>
                        <p>${SANPHAM.productdetail}</p>
                       <div class="fb-comments" data-href="https://www.facebook.com/${SANPHAM.productid}" data-width="768" data-numposts="5"></div>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="/footer.jsp"></jsp:include>
        <div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = 'https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.10&appId=1764636193833869';
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
    </body>
     <script src="js/jquery.js"></script>
  <script src="dist/jquery-scrollToTop.js"></script>
  <script type="text/javascript">
    $(document).ready(function($) {
      $('body').scrollToTop({
        skin: 'cycle'
      });
    });
  </script>
  <!-- $CHAT -->
<script language="javascript">
    var f_chat_vs = "Version 2.1";
    var f_chat_domain =  "index.jsp";    
    var f_chat_name = "Hỗ trợ độc giả";
    var f_chat_star_1 = "Chào bạn!";
    var f_chat_star_2 = 'Bạn có thể gửi câu hỏi về <a target="_blank" href="https://kuminhdey.com/">Hỏi đáp </a> hoặc trao đổi với tôi tại đây.<br/><em>Gửi vài giây trước</em>';
    var f_chat_star_3 = "<a href='javascript:;' onclick='f_bt_start_chat()' id='f_bt_start_chat'>Bắt đầu Chat</a>";
    var f_chat_star_4 = "Chú ý: Bạn phải đăng nhập <a href='http://facebook.com/' target='_blank' rel='nofollow'>Facebook</a> mới có thể trò chuyện.";
    var f_chat_fanpage = "https://www.facebook.com/Minh-%C4%90%E1%BB%A9c-475530282839486/"; /* Đây là địa chỉ Fanpage*/
    var f_chat_background_title = "#d23091"; /* Lấy mã màu tại đây http://megapixelated.com/tags/ref_colorpicker.asp */
    var f_chat_color_title = "#fff";
    var f_chat_cr_vs = 21; /* Version ID */
    var f_chat_vitri_manhinh = "right:70px;"; /* Right: 10px; hoặc left: 10px; hoặc căn giữa left:45% */    
</script>
<!-- $Chat iCon Font (có thể bỏ) -->
<!-- $Chat Javascript (không được xóa) -->
<script src="https://anonyviet.com/php/livechat/chat.js?vs=2.1"></script>
<!-- $Chat HTML (không được xóa) -->
<div id='fb-root'></div>
<a title='Mở hộp Chat' id='chat_f_b_smal' onclick='chat_f_show()' class='chat_f_vt'><i class='fa fa-comments title-f-chat-icon'></i> Chat</a><div id='b-c-facebook' class='chat_f_vt'><div id='chat-f-b' onclick='b_f_chat()' class='chat-f-b'><i class='fa fa-comments title-f-chat-icon'></i><label id="f_chat_name"></label><span id='fb_alert_num'>1</span><div id='t_f_chat'><a href='javascript:;' onclick='chat_f_close()' id='chat_f_close' class='chat-left-5'>x</a></div></div><div id='f-chat-conent' class='f-chat-conent'><script>document.write("<div class='fb-page' data-tabs='messages' data-href='https://www.facebook.com/"+f_chat_fanpage+"' data-width='250' data-height='310' data-small-header='true' data-adapt-container-width='true' data-hide-cover='true' data-show-facepile='false' data-show-posts='true'></div>");</script><div id='fb_chat_start'><div id='f_enter_1' class='msg_b fb_hide'></div><div id='f_enter_2' class='msg_b fb_hide'></div><br/><p id='f_enter_3' class='fb_hide' align='center'><a href='javascript:;' onclick='f_bt_start_chat()' id='f_bt_start_chat'>Bắt đầu Chat</a></p><br/><p id='f_enter_4' class='fb_hide' align='center'></p></div><div id="f_chat_source" class='chat-single'></div></div></div>
<!-- #CHAT -->
</html>
