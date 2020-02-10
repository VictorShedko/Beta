<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 28.01.2020
  Time: 22:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="pagecontent">
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
                                <input type="hidden" name="command" value="logout"/>
                                <button type="submit" class="btn btn-primary">log out</button>
                            </div>
                        </form>
                    </li>
                </div>
                <div class="col">
                    <li class="nav-item justify-content-start">
                        <form action="cleaning" method="POST">
                            <div class="form-group">
                                <input type="hidden" name="command" value="to_notify"/>
                                <button type="submit" class="btn btn-primary">notification</button>
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

        <div class="col">

            <p>${operationHead}</p>
        </div>
        <div class="col">
            <p>${operationResult}</p>
        </div>
        <div class="col">
            <form action="cleaning" method="POST">
                <div class="form-group">
                    <input type="hidden" name="command" value="to_user_menu"/>
                    <button type="submit" class="btn btn-primary"> <fmt:message key="operationResult.toMenu.button"/></button>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
</fmt:bundle>