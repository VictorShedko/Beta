<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 22.01.2020
  Time: 17:12
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
    <title><fmt:message key="availible-orders.title"/> </title>
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

<fmt:setLocale value="be_BY"/>
<div class="container">
<h2><fmt:message key="table.order.new"/> </h2>
<table class="table table-striped" id="tabl">
    <thead>
    <tr>
        <th>#</th>
        <th><fmt:message key="entity.oreder.customer"/></th>
        <th><fmt:message key="entity.oreder.start"/></th>
        <th><fmt:message key="entity.oreder.end"/></th>
        <th><fmt:message key="entity.oreder.address"/></th>
        <th><fmt:message key="entity.oreder.price"/></th>
        <th><fmt:message key="entity.oreder.description"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${availableOrderList}" var="item" varStatus="currentNumber">

        <tr>
            <td class="table-dark"><c:out value="${currentNumber.count}"/></td>
            <td class="table-dark"><c:out value="${item.customer}"/></td>
            <td class="table-dark"><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${item.startTime}"/></td>
            <td class="table-dark"><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${item.endTime}"/></td>
            <td class="table-dark"><c:out value="${item.address}"/></td>
            <td class="table-dark"><c:out value="${item.price} $"/></td>
            <td class="table-light"><c:out value="${item.description}"/></td>
            <td>
                <form action="cleaning" method="POST">
                    <input name="command" type="hidden" value="accept_order">
                    <input name="order" type="hidden" value="${item.orderId}">
                    <button type="submit" class="btn btn-primary"><fmt:message key="availible-orders.accept"/></button>
                </form>
            </td>

        </tr>

    </c:forEach>
    </tbody>
</table>

</div>
</body>
<script>
    $(document).ready(function(){
        $('#tabl').dataTable();
    });
</script>


</html>
</fmt:bundle>