<%@ page import = "pk.wieik.it.model.MNuser" %>
<%@ page import="pk.wieik.it.model.Tools" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    MNuser user = (MNuser) session.getAttribute("user");
    if (user == null){
        user = new MNuser();
        session.setAttribute("user", user);
    }
%>
<%--User: <%=user.getPrivileges() %>--%>
<ul>
    <li><a href="?page=main">Main page</a></li>
    <li><a href="?page=quadratic">ax&sup2+bx+c</a></li>
    <li><a href="?page=third">Third</a></li>
</ul>
<div id="news">
    <% if (user.getPrivileges() < 0) { %>
            <form id="loginForm" action="MN" method="post">
            <label for="login">Login:</label><br>
            <input type="text" id="login" name="login" size="15"><br>
            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" size="15"><br>
            <input type="submit" value="Login">
            </form> <%}

        else { %>
            <p id="loggedInMessage">[[LOGIN]]</p>

            <form id="logoutForm" action="MN" method="post">
                <input type="hidden" name="logout" value="true">
                <input type="submit" value="Logout">
            </form>
            <% } %>

    <p id = "news1"></p>
    <p id = "news2"></p>

</div>
