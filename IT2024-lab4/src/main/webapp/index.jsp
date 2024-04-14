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

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1"/>
    <title>IT-Lab1</title>
    <link rel="stylesheet" type="text/css" href="styl.css"/>
    <script type="text/javascript" src="script.js"></script>
    <script type="text/javascript" src="quadratic.js"></script>
</head>
<body onload="functions(); clock(); setInterval(clock, 1000); hook()">
<div id="container">
    <div id="header">
        <jsp:include page="/WEB-INF/view/header.jsp"/>
    </div>
    <div id="middle">
        <div id="menu">
            <jsp:include page="/WEB-INF/view/menu.jsp"/>
        </div>
        <div id="content">
            <jsp:include page="/WEB-INF/view/main.jsp"/>
            <jsp:param name="what_page" value="<%=Page%>"/>
            <jsp:include>
        </div>
    </div>
    <div id="footer">
        <jsp:include page="/WEB-INF/view/footer.jsp"/>
    </div>
</div>
</body>
</html>