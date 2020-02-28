<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 18.01.2020
  Time: 21:28
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
        <title><fmt:message key="customeOrderHistory.title"/></title>
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
    <div class="container">
        <div class="row">

            <H2><fmt:message key="customeOrderHistory.new-orders"/> </H2>
            <table class="table table-striped" id="new-orders">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="customeOrderHistory.table.head.executor"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.startTime"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.finishTime"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.description"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.address"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.price"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${newOrderList}" var="item" varStatus="currentNumber">

                    <tr>
                        <td class="table-light"><c:out value="${currentNumber.count}"/></td>
                        <td class="table-light"><c:out value="${item.executor}"/></td>
                        <td class="table-light"><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
                                                                value="${item.startTime}"/></td>
                        <td class="table-light"><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
                                                                value="${item.endTime}"/></td>
                        <td class="table-light"><c:out value="${item.address}"/></td>
                        <td class="table-light"><c:out value="${item.price}"/></td>
                        <td>
                            <form action="cleaning" method="POST">
                                <input name="command" type="hidden" value="delete_order">
                                <input name="order" type="hidden" value="${item.orderId}">
                                <button type="submit" class="btn btn-primary"><fmt:message
                                        key="customeOrderHistory.table.body.cancel.text"/></button>
                            </form>
                        </td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="row">
            <H2><fmt:message key="customeOrderHistory.completed-orders"/> </H2>
            <table class="table table-striped" id="completed-orders">
                <thead>
                <tr>
                    <th>#</th>
                    <th><fmt:message key="customeOrderHistory.table.head.executor"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.startTime"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.finishTime"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.description"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.address"/></th>
                    <th><fmt:message key="customeOrderHistory.table.head.price"/></th>

                </tr>
                </thead>
                <tbody>
                <c:forEach items="${completedOrderList}" var="item" varStatus="currentNumber">

                    <tr>
                        <td class="table-dark"><c:out value="${currentNumber.count}"/></td>
                        <td class="table-dark"><c:out value="${item.executor}"/></td>
                        <td class="table-dark"><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
                                                               value="${item.startTime}"/></td>
                        <td class="table-dark"><fmt:formatDate pattern="yyyy-MM-dd hh:mm"
                                                               value="${item.endTime}"/></td>
                        <td class="table-dark"><c:out value="${item.address}"/></td>
                        <td class="table-dark"><c:out value="${item.price}"/></td>
                        <td class="table-dark"><c:out value="${item.status.toString()}"/></td>
                    </tr>

                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    </body>
    <script>
        $(document).ready(function(){
            $('#new-orders').dataTable();
        });
    </script>
    <script>
        $(document).ready(function(){
            $('#completed-orders').dataTable();
        });
    </script>
    </html>

</fmt:bundle>
