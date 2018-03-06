

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>

        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/admin/mos-css/mos-style1.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%
             session.setAttribute("PAGECART", "index.jsp");
            if (session != null) {
                if (session.getAttribute("LOGIN") == "OK") {
                    response.sendRedirect("admin.jsp");
                } else if (session.getAttribute("LOGIN") == "FAIL") {
                    out.println("<script type=\"text/javascript\">");
                    out.println("alert('Đăng Nhập Thất Bại');");
                    out.println("</script>");
                }
            }
        %>

        <div id="header">
            <div class="inHeaderLogin"></div>
        </div>
        <div id="loginForm">
            <div class="headLoginForm">
               <i class="fa fa-sign-in" aria-hidden="true"></i> Login Administrator
            </div>
            <div class="fieldLogin">
                <form method="get" action="LoginServlet">
                    <label><i class="fa fa-user-circle-o" aria-hidden="true"></i> Username</label><br>
                    <input type="text" class="login" name="user"><br>
                    <label><i class="fa fa-key" aria-hidden="true"></i> Password</label><br>
                    <input type="password" class="login" name="pass"><br>
                    <button class="button" type="submit" value="Login" >Login <i class="fa fa-unlock-alt" aria-hidden="true"></i></button>
                </form>
            </div>
        </div>

    </body>
</html>
