<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 22.12.2019
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="pagecontent">

    <html>
    <head>
        <title>Error</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
    <div class="container">
        <p>Something went wrong!</p>
        <p>${errorMessage}</p>
        <form>
            <input type="hidden" name="command" value="logout">
            <button class="btn btn-primary" type="submit">
                <fmt:message key="error.to-login"/>
            </button>
        </form>
    </div>
    </body>
    </html>
</fmt:bundle>