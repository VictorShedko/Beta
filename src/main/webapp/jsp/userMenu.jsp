<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 15.01.2020
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                <input type="hidden" name="command" value="log_out"/>
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

        <div class="col">
            <p>photo here</p>
        </div>
        <div class="col">
            <p>Name:</p>
        </div>
        <div class="col">
            <p>Alesha</p>
        </div>
    </div>
    <div class="row">

        <div class="col">
            <p>Status:</p>
        </div>
        <div class="col">
            <p>Active</p>
        </div>
        <div class="col">
            <p>Balanse</p>
        </div>
        <div class="col">
            <p>5.0$</p>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <form action="cleaning" method="POST">
                <div class="form-group">
                    <input type="hidden" name="command" value="log_out"/>
                    <button type="submit" class="btn btn-primary">Make order</button>
                </div>
            </form>
        </div>
        <div class="col">
            <form action="cleaning" method="POST">
                <div class="form-group">
                    <input type="hidden" name="command" value="log_out"/>
                    <button type="submit" class="btn btn-primary">Show History</button>
                </div>
            </form>
        </div>
        <div class="col">
            <form action="cleaning" method="POST">
                <div class="form-group">
                    <input type="hidden" name="command" value="log_out"/>
                    <button type="submit" class="btn btn-primary">Add info</button>
                </div>
            </form>
        </div>

    </div>
</div>
</body>
</html>
