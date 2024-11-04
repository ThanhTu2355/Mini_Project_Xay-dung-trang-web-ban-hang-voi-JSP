<%-- 
    Document   : login
    Created on : 4 Nov 2024, 10:45:21
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--nhung noi dung header.jsp-->
<jsp:include page="shared/header.jsp" />
<!--nhung noi dung nav.jsp-->
<jsp:include page="shared/nav.jsp" />

<div class="container">
    <h2>Login System</h2>
    <form action="LoginServlet" method="post">
        <div>
            <label>User name</label>
            <input type="text" name="username" value="" class="form-control"></input>
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password" value="" class="form-control"></input>
        </div>
        <div>
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </form>
    <%
        if(request.getAttribute("error")!=null){
    %>
    <div class="=text-danger mb-3">
        <%=request.getAttribute("error")%>
    </div>
    <%
        }
    %>
</div>





<jsp:include page="shared/footer.jsp" /> 