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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="pagecontent">
    <html>
    <head>
        <title><fmt:message key="operationResult.title"/></title>
        <link href="<c:url value="/css/background.css" />" rel="stylesheet">
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

    <div class="mainContent">
        <div class="container">
            <div class="row">


                <div class="col">
                    <p style="color: red">${commandResult}</p>
                    <c:if test="${commandResult!= null}">
                        <p><fmt:message key="${commandResult}"/></p>

                    </c:if>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <form action="cleaning" method="Get">
                        <div class="form-group">
                            <input type="hidden" name="command" value="to_user_menu"/>
                            <button type="submit" class="btn btn-primary"><fmt:message
                                    key="operationResult.toMenu.button"/></button>
                        </div>
                    </form>
                </div>
                <div class="col">
                    <button type="submit" class="btn btn-primary" onclick="window.history.back()"><fmt:message
                            key="operationResult.back.button"/></button>
                </div>
            </div>
        </div>

    </div>
    </body>

    </html>
</fmt:bundle>