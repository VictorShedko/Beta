<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 22.01.2020
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<body>
<nav id="navbar-example2" class="navbar navbar-light bg-light">
    <div class="container">
        <div class="row">

            <ul class="nav nav-pills">
                <div class="col">
                    <li class="nav-item justify-content-start">
                        <form action="cleaning" method="POST">
                            <div class="form-group">
                                <input type="hidden" name="command" value="to_user_menu"/>
                                <button type="submit" class="btn btn-primary">User menu</button>
                            </div>
                        </form>
                    </li>
                </div>
                <div class="col justify-content-end">
                    <li class="nav-item">
                        <form action="cleaning" method="POST">
                            <div class="form-group">
                                <input type="hidden" name="command" value="log_out"/>
                                <button type="submit" class="btn btn-primary">log out</button>
                            </div>
                        </form>
                    </li>
                </div>
            </ul>


        </div>
    </div>
</nav>

<fmt:setLocale value="be_BY"/>
<table class="table table-striped">
    <thead>
    <tr>
        <th>#</th>
        <th>заказчик</th>
        <th>начало уборки</th>
        <th>оканчание уборки</th>
        <th>адресс</th>
        <th>цена</th>
        <th>описание</th>
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


</body>
</html>
</fmt:bundle>