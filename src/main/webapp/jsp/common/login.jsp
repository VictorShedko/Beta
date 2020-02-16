<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 13.01.2020
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="pagecontent">
    <html>
    <head>
        <title><fmt:message key="homepage.title"/></title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    </head>
    <body>
    <div class="container">
        <div class="row ">
            <div class="col justify-content-start">
                <p><fmt:message key="login.welcome.text"/></p>
            </div>
        </div>
        <div class="row">
            <div class="col ">
                <form action="cleaning" method="POST" class="needs-validation" novalidate>
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="login" lass="col-sm-2 col-form-label"> <fmt:message key="login.loginfield.text"/></label>
                                <input type="text" class="form-control " required minlength="6" id="login"
                                       name="login">
                                <div class="valid-feedback">
                                    <fmt:message key="login.loginfield.feedback.valid"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <div class="form-group">
                                <label for="inputPassword" class="col-sm-2 col-form-label"><fmt:message key="login.password.label"/></label>

                                <input type="password" class="form-control" required minlength="6" maxlength="18"
                                       id="inputPassword"
                                       placeholder="Password"
                                       name="password">
                                <div class="valid-feedback">
                                    <fmt:message key="login.password.feedback.valid"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class=" row">
                        <div class="col-sm-3">
                            <div class="form-group">
                                <div class="form-group">
                                    <input type="hidden" name="command" value="login"/>
                                    <button type="submit" class="btn btn-primary"><fmt:message
                                            key="login.button.login.text"/></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class=" row">
            <p>${feedback}</p>
        </div>
        <div class=" row">
            <form action="cleaning" method="POST">
                <div class="form-group">
                    <input type="hidden" name="command" value="to_registration"/>
                    <button type="submit" class="btn btn-primary"><fmt:message
                            key="login.button.registration.text"/></button>
                </div>
            </form>
        </div>

    </div>
    <script type="text/javascript">
        const form = document.querySelector('.needs-validation')

        form.addEventListener('submit', (e) => {
            if (form.checkValidity() === false) {
                e.preventDefault();
            }

            if (form.license.checked === false) {
                e.preventDefault();
            }
            alert("lol");
            form.classList.add('was-validated');
        })

    </script>

    <script type="text/javascript" src="../../js/common.js"/>
    </body>
    </html>



</fmt:bundle>
