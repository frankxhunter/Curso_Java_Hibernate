<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <jsp:include page="layout/header.jsp" />
<h1>Realiza el login</h1>
<form action="/listeners-filters/login" method="post">
    <label for="username">Username: </label>
    <input type="text" name="username" id="username">

    <label for="password">Password: </label>
    <input type="password" name="password" id="password">

    <input type="submit" value="Login">
</form>
<jsp:include page="layout/footer.jsp"/>