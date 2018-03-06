

<%@page import="model.CategoryDAO"%>
<%@page import="model.CategoryDAO"%>
<%@page import="model.ProductDAO"%>
<%@page import="java.util.Vector"%>
<%@page import="model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% ProductDAO proDAO = new ProductDAO(); %>
<% CategoryDAO cateDAO = new CategoryDAO();%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Sản Phẩm</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/admin/mos-css/mos-style1.css" rel='stylesheet' type='text/css' />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
        <script src="../ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <%
            if (session != null) {
                if (session.getAttribute("LOGIN") == null) {
                    response.sendRedirect("");
                }
            }
        %>
        <jsp:include page="header.jsp"></jsp:include>

            <div id="wrapper">

            <jsp:include page="menu.jsp"></jsp:include>
                <div id="rightContent">
                    <h3><i class="fa fa-product-hunt" aria-hidden="true"></i> Quản Lý Sản Phẩm</h3>
                    <form action="Upload" method="post" enctype="multipart/form-data" acceptcharset="UTF-8">
                        <input type="hidden" class="sedang" readonly name="productid" value="${PRODUCT.productid}" >
                    <table style="float: right;">
                        <img id="blah" src="../images/${PRODUCT.productimage}" style="height: 150px;width: 150px;">
                        <tbody>

                            <tr>
                                <td><b>Tên Danh Mục:</b></td>
                                <td><select name="category">
                                        <c:forEach items="${CATEGORYNAME}" var="danhmuc">
                                            <option value="${danhmuc.categoryid}" ${danhmuc.categoryid == PRODUCT.category.categoryid ? 'selected="selected"' : ''} >${danhmuc.categoryname}</option>
                                        </c:forEach>
                                    </select></td>
                                <td><b>Tên Sản Phẩm:</b></td>
                                <td><input type="text" placeholder="Nhập tên sản phẩm" class="sedang" name="productname" value="${PRODUCT.productname}" required></td>
                            </tr>
                            <tr>
                                <td><b>Hình sản phẩm:</b></td>
                                <td><input type="hidden" value="${PRODUCT.productimage}" name="productimage"  /><input type="file" name="Uploadfile" id="imgInp"/></td>
                                <td><b>Giá Sản Phẩm:</b></td>
                                <td><input type="number" placeholder="Nhập giá sản phẩm" class="sedang" name="productprice" value="${PRODUCT.productprice}" required></td>
                            </tr> 
                            <tr>
                                <td><b>Chi tiết sản phẩm:</b></td>
                            </tr>

                        </tbody>
                    </table>
                    <textarea class="form-textarea" id="noiDung" name="productdetail">${PRODUCT.productdetail}</textarea>

                    <table align="center">
                        <tbody>
                           
                            <tr>
                                <td>
                                    <button type="submit" class="button" name="action" value="Add New">Add New <i class="fa fa-plus-circle" aria-hidden="true"></i></button>
                                </td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td>
                                    <button type="submit" class="button" name="action" value="Update">Update <i class="fa fa-plus-circle" aria-hidden="true"></i></button>

                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <table class="data">
                    <tbody><tr class="data">
                            <th class="data" width="30px">STT</th>
                            <th class="data">ID</th>
                            <th class="data">Tên Danh Mục</th>
                            <th class="data">Tên Sản Phẩm</th>
                            <th class="data">Giá Sản Phẩm</th>
                            <th class="data">Hình Sản Phẩm</th>
                            <th class="data">Chi Tiết</th>
                            <th class="data" width="75px">Chức Năng</th>
                        </tr>
                        <%
                            int count = 0;
                            for (int i = 0; i < proDAO.getAllProduct("").size(); i++) {
                                count++;
                        %>
                        <tr class="data">
                            <td class="data" width="30px"><%=count%></td>
                            <td class="data"><%= proDAO.getAllProduct("").get(i).getProductid()%></td>                          
                            <td class="data"><%= proDAO.getAllProduct("").get(i).getCategory().getCategoryname()%></td>
                            <td class="data"><%= proDAO.getAllProduct("").get(i).getProductname()%></td>
                            <td class="data"><%= proDAO.getAllProduct("").get(i).getProductprice()%></td>
                            <td class="data"><%= proDAO.getAllProduct("").get(i).getProductimage()%></td>
                            <td class="data"><%= proDAO.getAllProduct("").get(i).getProductdetail()%></td>
                            <td class="data" width="75px">
                    <center>
                        <a href='<%="ManagerProductServlet?action=edit&productid=" + proDAO.getAllProduct("").get(i).getProductid()%>'><i class="fa fa-wrench" aria-hidden="true"></i></a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        <a href='<%="ManagerProductServlet?action=del&productid=" + proDAO.getAllProduct("").get(i).getProductid() + "&productname=" + proDAO.getAllProduct("").get(i).getProductname()%>'><i class="fa fa-trash" aria-hidden="true"></i></a>
                            <% }%>
                    </center>
                    </td>
                    </tr>

                    </tbody></table>
            </div>
            <div class="clear"></div>

            <jsp:include page="footer.jsp"></jsp:include>

        </div>

    </body>
    <script>
        function readURL(input) {

            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#blah').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        $("#imgInp").change(function () {
            readURL(this);
        });
    </script>
    <script type="text/javascript" language="javascript">
        CKEDITOR.replace('noiDung', {width: '700px', height: '200px'});
    </script>
</html>
