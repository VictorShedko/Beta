<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 25.01.2020
  Time: 23:16
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
    <title><fmt:message key="userList.title"/></title>
    <link href="<c:url value="/css/background.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
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
<div class="mainContent">
<div class="container">

    <h2><fmt:message key="table.order.new"/> </h2>
    <p><fmt:message key="${searchParameter}"/></p>
    <p>${searchParameterValue}</p>
    <table class="table table-striped" id="user" >
        <thead>
        <tr>
            <th>#</th>
            <th><fmt:message key="userList.table.head.name"/></th>
            <th><fmt:message key="userList.table.head.login"/></th>
            <th><fmt:message key="userList.table.head.state"/></th>
            <th><fmt:message key="userList.table.head.mail"/></th>
            <th><fmt:message key="userList.table.head.registration.time"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userList}" var="item" varStatus="currentNumber">

            <tr>
                <td class="table-dark"><c:out value="${currentNumber.count}"/></td>
                <td class="table-dark"><c:out value="${item.username}"/></td>
                <td class="table-dark"><fmt:formatDate pattern="yyyy-MMM-dd hh:mm" value="${item.registrationTime}"/></td>
                <td class="table-dark"><c:out value="${item.email}"/></td>
                <td class="table-dark"><c:out value="${item.login}"/></td>
                <td class="table-dark"><c:out value="${item.status.toString()}"/></td>
                <td class="table-dark"><c:out value="${item.role.toString()}"/></td>
                <td class="table-light">
                    <form action="cleaning" method="get">
                        <input type="hidden" name="command" value="show_user_profile"/>
                        <input name="searchName" type="hidden" value="${item.username}">
                        <button type="submit" class="btn btn-primary"><fmt:message key="userList.table.body.openProfile"/></button>
                    </form>
                </td>

            </tr>

        </c:forEach>
        </tbody>
    </table>
</div>
</div>
</body>
<script>
    $(document).ready(function(){
        $('#user').dataTable();
    });
</script>
</html>
</fmt:bundle>