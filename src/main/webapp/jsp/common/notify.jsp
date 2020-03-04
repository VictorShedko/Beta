<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 22.01.2020
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="pagecontent">

<html>
<head>
    <title> <fmt:message key="notification.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<nav id="navbar" class="navbar navbar-light bg-light">


    <ul class="nav nav-pills">



        <li class="nav-item justify-content-start">
            <form action="cleaning" method="GET" style="display: inline-block">
                <div class="form-group">
                    <input type="hidden" name="command" value="to_user_menu"/>
                    <button type="submit" class="btn btn-primary"><fmt:message key="navbar.usermenu"/></button>
                </div>
            </form>
        </li>

        <div class="col justify-content-end">
            <li class="nav-item">
                <form action="cleaning" method="GET">
                    <div class="form-group">
                        <input type="hidden" name="command" value="logout"/>
                        <button type="submit" class="btn btn-primary"><fmt:message key="navbar.logout"/></button>
                    </div>
                </form>
            </li>
        </div>
        <div class="col">
            <li class="nav-item justify-content-start">
                <form action="cleaning" method="GET">
                    <div class="form-group">
                        <input type="hidden" name="command" value="to_notify"/>
                        <button type="submit" class="btn btn-primary"><fmt:message
                                key="navbar.notification"/></button>
                    </div>
                </form>
            </li>
        </div>

        <div class="col">
            <li class="nav-item justify-content-end">
                <form action="cleaning" method="GET">
                    <div class="form-group">
                        <input type="hidden" name="command" value="change_locale"/>
                        <button type="submit" class="btn btn-primary"><fmt:message
                                key="navbar.changelaguge"/></button>
                    </div>
                </form>
            </li>
        </div>
        <div class="col">
            <ctg:userInfo/>
        </div>
    </ul>


</nav>


<table class="table table-striped">
    <thead>
    <tr>
        <th>#</th>
        <th> <fmt:message key="notification.table.head.text"/></th>
        <th> <fmt:message key="notification.table.head.time"/></th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${notifyList}" var="item" varStatus="currentNumber">

        <ctg:notification notification="${item}"/>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
</fmt:bundle>