

<%@page import="model.billDAO"%>
<%@page import="model.CategoryDAO"%>
<%@page import="model.CategoryDAO"%>
<%@page import="model.ProductDAO"%>
<%@page import="java.util.Vector"%>
<%@page import="model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% ProductDAO proDAO = new ProductDAO(); %>
<% CategoryDAO cateDAO = new CategoryDAO();%>
<% billDAO billDAO = new billDAO();%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Hóa Đơn</title>
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/admin/mos-css/mos-style1.css" rel='stylesheet' type='text/css' />
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
                    <h3><i class="fa fa-btc" aria-hidden="true"></i> Quản lý Hóa Đơn</h3>

                    <table class="data">
                        <tbody><tr class="data">

                                <th class="data" width="30px">STT</th>
                                <th class="data">ID</th>
                                <th class="data">ID Người Dùng</th>
                                <th class="data">Tên Người Nhận</th>
                                <th class="data">Số Điện Thoại</th>
                                <th class="data">Địa Chỉ</th>
                                <th class="data">Tổng Tiền</th>
                                <th class="data">Xác Nhận</th>
                                <th class="data" width="75px">Chức Năng</th>
                            </tr>
                        <%
                            int count = 0;
                            for (int i = 0; i < billDAO.getAllBill().size(); i++) {
                                count++;
                        %>
                        <tr class="data">
                            <td class="data" width="30px"><%=count%></td>
                            <td class="data"><%= billDAO.getAllBill().get(i).getIdHoaDon() %></td>                          
                            <td class="data"><%= billDAO.getAllBill().get(i).getNguoiDung().getUserName() %></td>
                            <td class="data"><%= billDAO.getAllBill().get(i).getTenNguoiNhan() %></td>
                            <td class="data"><%= billDAO.getAllBill().get(i).getSoDienThoai() %></td>
                            <td class="data"><%= billDAO.getAllBill().get(i).getDiaChi() %></td>
                            <td class="data"><%= billDAO.getAllBill().get(i).getTongTien() %></td>
                            <td class="data"><center> <%= billDAO.getAllBill().get(i).getXacNhan() %> </center></td>
                            <td class="data" width="100px">
                    <center>
                        <a href='<%="ManagerBillServlet?action=confirm&idbill=" + billDAO.getAllBill().get(i).getIdHoaDon()%>'><i class="fa fa-check-square-o" aria-hidden="true"></i></a>&nbsp;&nbsp;|&nbsp;&nbsp;
                        <a href='<%="ManagerBillServlet?action=del&idbill=" + billDAO.getAllBill().get(i).getIdHoaDon()%>'><i class="fa fa-trash" aria-hidden="true"></i></a>
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
</html>
