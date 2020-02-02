<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 25.01.2020
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <form action="cleaning" method="POST">
        <div class="form-row">
            <div class="form-group">
                <label for="creditSum">Price</label>
                <input type="number" class="form-control" id="creditSum" name="creditSum">
            </div>
            <input type="hidden" name="command" value="credit_account">
            <button type="submit" class="btn btn-primary">Create</button>
        </div>
    </form>
</div>
</body>
</html>