<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 13.01.2020
  Time: 14:10
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
        <title>Title</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
    <div class="container">
        <div class="row">
        <h1>Welcome, fill this form, please</h1>
        </div>
        <div class="row">
        <form action="cleaning" method="post" class="needs-validation" novalidate>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputName"> <fmt:message key="registration.label.username.text"/></label>
                    <input type="text" class="form-control" id="inputName" placeholder="name" name="username" required
                           minlength="6" maxlength="25">
                    <div class="valid-feedback">
                        <fmt:message key="registration.username.feedback.valid"/>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <label for="inputPassword"><fmt:message key="registration.label.password.text"/></label>
                    <input type="password" class="form-control" id="inputPassword" placeholder="Password"
                           name="password" required minlength="6" maxlength="25">
                    <div class="valid-feedback">
                        <fmt:message key="registration.username.feedback.valid"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-group col-md-6">
                    <label for="inputEmail"><fmt:message key="registration.label.email.text"/></label>
                    <input type="email" class="form-control" id="inputEmail" name="email">
                    <div class="valid-feedback">
                        <fmt:message key="registration.username.feedback.valid"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="form-group col-md-6">
                    <label for="inputLogin">Login</label>
                    <input type="text" class="form-control" id="inputLogin" placeholder="login" name="login" required
                           minlength="6" maxlength="25">
                    <div class="valid-feedback">
                        <fmt:message key="registration.username.feedback.valid"/>
                    </div>
                </div>
            </div>
            <div class="form-group">

                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="role" id="CustomerRadio"
                           value="customer" checked>
                    <label class="form-check-label" for="CustomerRadio">
                        <fmt:message key="registration.label.role.customer.text"/>
                    </label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="role" id="ExecutorRadio"
                           value="executor">
                    <label class="form-check-label" for="ExecutorRadio">
                        <fmt:message key="registration.label.role.cleaner.text"/>
                    </label>
                </div>
            </div>


            <div class="form-group">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" name="license" id="gridCheck" required value="">
                    <label class="form-check-label" for="gridCheck">
                        <fmt:message key="registration.license.check"/>
                    </label>
                </div>
            </div>
            <input type="hidden" name="command" value="register"/>
            <button type="submit" class="btn btn-primary"><fmt:message key="registration.signin.bytton.text"/></button>
        </form>

        <div class=" row">
            <p>${feedback}</p>
        </div>
        </div>
    </div>
    </body>
    <script src="<c:url value="/js/common.js" />"></script>
    <script src="<c:url value="/js/eula.js" />"></script>
    </html>
</fmt:bundle>