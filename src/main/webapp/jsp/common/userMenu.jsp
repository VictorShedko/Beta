<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 15.01.2020
  Time: 17:53
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

        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Cache-Control" content="no-store">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <title><fmt:message key="menu.test"/></title>
    </head>
    <body>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">

    <nav id="navbar" class="navbar navbar-light bg-light">


                <ul class="nav nav-pills">
                    <li class="nav-item ">
                    <ctg:userInfo/>
                    <li class="nav-item ">
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
                                    <button type="submit" class="btn btn-primary"><fmt:message key="navbar.notification"/></button>
                                </div>
                            </form>
                        </li>
                    </div>
                    <div class="col">
                        <li class="nav-item justify-content-start">
                            <form action="cleaning" method="GET">
                                <div class="form-group">
                                    <input type="hidden" name="command" value="change_locale"/>
                                    <button type="submit" class="btn btn-primary"><fmt:message key="navbar.changelaguge"/></button>
                                </div>
                            </form>
                        </li>
                    </div>
                </ul>



    </nav>

    <div class="container">
        <div class="row">

            <div class="col">
                <img src="<c:url value="${photoPath}"/>" alt="not found">
            </div>
            <div class="col">
                <p><fmt:message key="userMenu.name.text"/></p>
            </div>
            <div class="col">
                <p><c:out value="${username}"/></p>
            </div>
        </div>
        <div class="row">

            <div class="col">
                <p><fmt:message key="userMenu.status.text"/></p>
            </div>
            <div class="col">
                <c:out value="${status.toString()}"/>
            </div>
            <div class="col">
                <p><fmt:message key="userMenu.balance.text"/></p>
            </div>
            <div class="col">
                <p>${balance}$</p>
            </div>

            <div class="col">
                <form action="cleaning" method="GET">
                    <div class="form-group">
                        <input type="hidden" name="command" value="to_credit_form"/>
                        <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.credit.button.text"/></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col">
            <form action="cleaning" method="GET">
                <div class="form-group">
                    <input type="hidden" name="command" value="to_add_info"/>
                    <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.customer.addInfo.text"/></button>
                </div>
            </form>
        </div>
        <div class="col">
            <form action="cleaning" method="GET">
                <div class="form-group">
                    <input type="hidden" name="command" value="refresh"/>
                    <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.new.refresh.text"/></button>
                </div>
            </form>
        </div>
        <c:if test="${status.toString() == 'NEW'}">
        <div class="col">
            <form action="cleaning" method="GET">
                <div class="form-group">
                    <input type="hidden" name="command" value="resend_email"/>
                    <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.new.resend.text"/></button>
                </div>
            </form>
        </div>
        </c:if>

        <c:if test="${status.toString() == 'VERIFIED'}">
        <div class="row">
            <c:if test="${role.value() == 'customer'}">
                <div class="col">
                    <form action="cleaning" method="GET">
                        <div class="form-group">
                            <input type="hidden" name="command" value="to_create_order_form"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.customer.makeOrder.text"/></button>
                        </div>
                    </form>
                </div>
                <div class="col">
                    <form action="cleaning" method="GET">
                        <div class="form-group">
                            <input type="hidden" name="command" value="show_orders_customer"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.customer.history.text"/></button>
                        </div>
                    </form>
                </div>
            </c:if>
            <c:if test="${role.value() == 'executor'}">
                <div class="col">
                    <form action="cleaning" method="GET">
                        <div class="form-group">
                            <input type="hidden" name="command" value="show_available_orders"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.executor.findOrder.button.text"/></button>
                        </div>
                    </form>
                </div>
                <div class="col">
                    <form action="cleaning" method="GET">
                        <div class="form-group">
                            <input type="hidden" name="command" value="show_my_documents"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.executor.mydocuments.button.text"/></button>
                        </div>
                    </form>
                </div>
                <div class="col">
                    <form action="cleaning" method="GET">
                        <div class="form-group">
                            <input type="hidden" name="command" value="show_orders_executor"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.executor.history.button.text"/></button>
                        </div>
                    </form>
                </div>
            </c:if>
            <c:if test="${role.value() == 'admin'}">
                <div class="col">
                    <form action="cleaning" method="GET">
                        <div class="form-group">
                            <input type="hidden" name="command" value="show_user_by_status"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.admin.search.status.bytton.text"/></button>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="status" id="newRadio"
                                       value="NEW" checked>
                                <label class="form-check-label" for="newRadio">
                                    <fmt:message key="userMenu.admin.search.status.new.text"/>
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="status" id="verifiedRadio"
                                       value="VERIFIED">
                                <label class="form-check-label" for="verifiedRadio">
                                    <fmt:message key="userMenu.admin.search.status.verified.text"/>
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="status" id="notVerifiedRadio"
                                       value="EMAIL_VERIFIED">
                                <label class="form-check-label" for="notVerifiedRadio">
                                    <fmt:message key="userMenu.admin.search.status.notVerified.text"/>
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="status" id="deletedRadio"
                                       value="DELETED">
                                <label class="form-check-label" for="deletedRadio">
                                    <fmt:message key="userMenu.admin.search.status.deleted.text"/>
                                </label>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col">
                    <form action="cleaning" method="GET">

                        <div class="form-group">
                            <input type="hidden" name="command" value="show_user_by_role"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.admin.search.role.bytton.text"/></button>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="CustomerRadio"
                                       value="customer" checked>
                                <label class="form-check-label" for="CustomerRadio">
                                    <fmt:message key="userMenu.admin.search.role.customer.text"/>
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="ExecutorRadio"
                                       value="executor">
                                <label class="form-check-label" for="ExecutorRadio">
                                    <fmt:message key="userMenu.admin.search.role.executor.text"/>
                                </label>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="col">
                    <form action="cleaning" method="GET">
                        <div class="form-group">
                            <input type="hidden" name="command" value="show_all_users"/>
                            <button type="submit" class="btn btn-primary"><fmt:message key="userMenu.admin.showall"/></button>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
        </c:if>
    </div>
    </body>

    </html>
</fmt:bundle>