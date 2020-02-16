<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 02.02.2020
  Time: 0:10
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
                    <div class="col">
                        <li class="nav-item justify-content-start">
                            <form action="cleaning" method="POST">
                                <div class="form-group">
                                    <input type="hidden" name="command" value="change_locale"/>
                                    <button type="submit" class="btn btn-primary">En</button>
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
                <img height="250" width="150" src="<c:url value="${profilePhoto}"/>" alt="not found">
                <p>photo here</p>
            </div>
            <div class="col">
                <p>Name: </p>
            </div>
            <div class="col">
                <p>${userInfo.username}</p>
            </div>
        </div>
        <div class="row">

            <div class="col">
                <p>Status:</p>
            </div>
            <div class="col">
                <c:out value="${userInfo.status}"/>
            </div>
            <div class="col">
                <p>Balanse</p>
            </div>
        </div>
        <c:if test="${role.value() == 'admin'}">
            <div class="row">
                <div class="col">
                    <form action="cleaning" method="POST">
                        <div class="form-group">

                            <c:if test="${userInfo.status.toString() == 'DELETED'}">
                                <input type="hidden" name="command" value="activate_deleted_user"/>
                                <input type="hidden" name="user" value="${user.username}"/>
                                <button type="submit" class="btn btn-primary">восстоновить</button>
                            </c:if>
                            <c:if test="${userInfo.status.toString() != 'DELETED'}">
                                <input type="hidden" name="command" value="show_user_by_status"/>
                                <input type="hidden" name="user" value="${user.username}"/>
                                <button type="submit" class="btn btn-primary">удалить</button>
                            </c:if>
                        </div>
                    </form>
                </div>
                <div class="col">
                    <form action="cleaning" method="POST">
                        <div class="form-group">
                            <c:if test="${userInfo.status.toString() == 'EMAIL_VERIFIED'}">
                                <input type="hidden" name="command" value="verify_user"/>
                                <input type="hidden" name="username" value="${userInfo.username}"/>
                                <button type="submit" class="btn btn-primary">верифицировать</button>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
        </c:if>
        <ctg:userInfo/>
    </div>
    <c:if test="${userInfo.role.value() == 'executor'}">
        <div class="row">
            <table class="table table-striped">
                <thead>
                </thead>
                <tbody>
                <c:forEach items="${documentList}" var="item" varStatus="currentNumber">

                    <c:if test="${item.adminName == null}">
                        <tr>
                            <td class="table-dark"><c:out value="${currentNumber.count}"/></td>
                            <td><img alt="ups" src="<c:url value="${item.file}"/>"/></td>
                            <c:if test="${role.value() == 'admin'}">
                                <td>
                                    <form action="cleaning" method="POST">
                                        <div class="form-group">

                                                <input type="hidden" name="command" value="check_document"/>
                                                <input type="hidden" name="admin" value="${username}"/>
                                                <input type="hidden" name="documentId" value="${item.id}"/>
                                                <button type="submit" class="btn btn-primary"><fmt:message
                                                        key="userProfile.admin.check.button"/></button>

                                        </div>
                                    </form>
                                </td>
                            </c:if>
                        </tr>
                    </c:if>
                    <c:if test="${item.adminName != null}">
                        <tr>
                            <td class="table-light"><c:out value="${currentNumber.count}"/></td>
                            <td><img src="<c:url value="${item.file}"/>"/></td>
                            <td>${item.adminName}</td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>


    </body>
    </html>
</fmt:bundle>