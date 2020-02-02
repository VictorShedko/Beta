
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 15.01.2020
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
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
                                <button type="submit" class="btn btn-primary">notify</button>
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
            <img src=""<c:url value=""/>">
            <img src="<c:url value="${photoPath}"/>">
            <p>photo here</p>
        </div>
        <div class="col">
            <p>Name: </p>
        </div>
        <div class="col">
            <p>${username}</p>
        </div>
    </div>
    <div class="row">

        <div class="col">
            <p>Status:</p>
        </div>
        <div class="col">
           <c:out value="${status}"/>
        </div>
        <div class="col">
            <p>Balanse</p>
        </div>
        <div class="col">
            <p>${balance}$</p>
        </div>
        <div class="col">
            <form action="cleaning" method="POST">
                <div class="form-group">
                    <input type="hidden" name="command" value="to_credit_form"/>
                    <button type="submit" class="btn btn-primary">credit</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <c:if test="${role.value() == 'customer'}">
            <div class="col">
                <form action="cleaning" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="command" value="to_create_order_form"/>
                        <button type="submit" class="btn btn-primary">Make order</button>
                    </div>
                </form>
            </div>
            <div class="col">
                <form action="cleaning" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="command" value="show_orders_customer"/>
                        <button type="submit" class="btn btn-primary">Show History</button>
                    </div>
                </form>
            </div>
            <div class="col">
                <form action="cleaning" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="command" value="to_add_info"/>
                        <button type="submit" class="btn btn-primary">Add info</button>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${role.value() == 'executor'}">
            <div class="col">
                <form action="cleaning" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="command" value="show_available_orders"/>
                        <button type="submit" class="btn btn-primary">Find order</button>
                    </div>
                </form>
            </div>
            <div class="col">
                <form action="cleaning" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="command" value="show_orders_executor"/>
                        <button type="submit" class="btn btn-primary">Show History</button>
                    </div>
                </form>
            </div>
            <div class="col">
                <form action="cleaning" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="command" value="to_add_info"/>
                        <button type="submit" class="btn btn-primary">Add info</button>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${role.value() == 'admin'}">
        <div class="col">
            <form action="cleaning" method="POST">
                <div class="form-group">
                    <input type="hidden" name="command" value="show_user_by_status"/>
                    <button type="submit" class="btn btn-primary">user list by status</button>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="status" id="newRadio"
                               value="new" checked>
                        <label class="form-check-label" for="newRadio">
                            new
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="status" id="verifiedRadio"
                               value="verified">
                        <label class="form-check-label" for="verifiedRadio">
                            verified
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="status" id="notVerifiedRadio"
                               value="not_verified">
                        <label class="form-check-label" for="notVerifiedRadio">
                            not verified
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="status" id="deletedRadio"
                               value="deleted">
                        <label class="form-check-label" for="deletedRadio">
                            deleted
                        </label>
                    </div>
                </div>
            </form>
        </div>
        <div class="col">
            <form action="cleaning" method="POST">

                <div class="form-group">
                    <input type="hidden" name="command" value="show_user_by_role"/>
                    <button type="submit" class="btn btn-primary">user list by role</button>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="role" id="CustomerRadio"
                               value="customer" checked>
                        <label class="form-check-label" for="CustomerRadio">
                            Покупатель
                        </label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="role" id="ExecutorRadio"
                               value="executor">
                        <label class="form-check-label" for="ExecutorRadio">
                            Уборщик
                        </label>
                    </div>
                </div>

        </form>
    </div>
    </c:if>

    </div>
</div>
</body>
</html>
