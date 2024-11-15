<%-- 
    Document   : register
    Created on : 11 Nov 2024, 10:07:28
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--nhung noi dung header.jsp-->
<jsp:include page="shared/header.jsp" />
<!--nhung noi dung nav.jsp-->
<jsp:include page="shared/nav.jsp" />
<div class="container">
    <h2>Register</h2>
    <form action="RegisterServlet" method="post">
        <div>
            <label>User name</label>
            <input type="text" name="tendangnhap" value="" class="form-control"></input>
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="matkhau" value="" class="form-control"></input>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Register</button>
        </div>
    </form>
</div>

<jsp:include page="shared/footer.jsp" /> 
