<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 14.01.2020
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
</head>
<body>
div class="container">
<div class="row ">
    <div class="col justify-content-center">
        <p>Welcome</p>
    </div>
</div>
<div class="row ">
    <div class="col ">
        <form class="needs-validation" novalidate>
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" id="name" class="form-control" required minlength="6" maxlength="8">
                        <div class="valid-feedback">Looks good!</div>
                        <div class="invalid-feedback">Enter correct name</div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-6 col-sm-8">
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" id="password" class="form-control" required>
                        <div class="valid-feedback">Password looks good!</div>
                        <div class="invalid-feedback">Enter correct password</div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <label for="gender">Gender</label>
                        <select name="gender" id="gender" class="form-control">
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <div class="form-check align-middle">
                            <input type="checkbox" id="remember" class="form-check-input">
                            <label for="remember" class="form-check-label">Remember</label>
                        </div>
                    </div>
                </div>
            </div>

            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
</div>
<script type="text/javascript">
    const form = document.querySelector('.needs-validation')
    alert("suka")
    form.addEventListener('submit', (e) => {
        if (form.checkValidity() === false) {
            e.preventDefault();
        }
        alert("suka")
        form.classList.add('was-validated');
    })
</script>
</body>
</html>
