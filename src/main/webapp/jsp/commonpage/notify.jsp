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
<%@ page isELIgnored="false" %>


<html>
<head>
    <title>Title</title>
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
        <th>текст</th>
        <th>время</th>


    </tr>
    </thead>
    <tbody>
    <c:forEach items="${notifyList}" var="item" varStatus="currentNumber">

        <tr>
            <td class="table-light"><c:out value="${currentNumber.count}"/></td>
            <td class="table-dark"><c:out value="${item.text}"/></td>
            <td class="table-light"><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${item.date}"/></td>
        </tr>

    </c:forEach>
    </tbody>
</table>

</body>
</html>