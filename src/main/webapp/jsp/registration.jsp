<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 13.01.2020
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<h1>Welcome, fill this form, please</h1>
<form onsubmit="registrationValidate(this)">
    <div class="form-row">
        <div class="form-group col-md-6">
            <label for="inputName">User name</label>
            <input type="text" class="form-control" id="inputName" placeholder="name">
        </div>
        <div class="form-group col-md-6">
            <label for="inputPassword">Password</label>
            <input type="password" class="form-control" id="inputPassword" placeholder="Password">
        </div>
    </div>
    <div class="form-group">
        <div class="form-group col-md-6">
            <label for="inputEmail">User name</label>
            <input type="email" class="form-control" id="inputEmail" placeholder="name">

        </div>
    </div>

    <div class="form-group">
        <label>Пожарный</label>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="CustomerRadio"
                   value="Customer" checked>
            <label class="form-check-label" for="CustomerRadio">
            Покупатель
            </label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="CopRadio"
                   value="Cop">
            <label class="form-check-label" for="CopRadio">
                Полицейский
            </label>
        </div>
        <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="role" id="FirefighterRadio"
                   value="Firefighter">
            <label class="form-check-label" for="FirefighterRadio">Пожарный
            </label>
        </div>
    </div>


    <div class="form-group">
        <div class="form-check">
            <input class="form-check-input" type="checkbox" id="gridCheck">
            <label class="form-check-label" for="gridCheck">
                Прочитал лецензионое соглашение и согласен
            </label>
        </div>
    </div>
    <button type="submit" class="btn btn-primary">Sign in</button>
</form>
</body>
<script src="../js/comon.js"/>
</html>
