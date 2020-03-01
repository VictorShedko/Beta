<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 15.01.2020
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="pagecontent">
    <%@ page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
    <title><fmt:message key="order-form.title"/> </title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class=" row">
        <p>${feedback}</p>
    </div>
    <form action="cleaning" method="POST" class="needs-validation" novalidate accept-charset="utf-8">
        <div class="form-row">
            <div class="form-group">
                <label for="inputAddress">Start time</label>
                <input type="datetime-local" class="form-control" name="startTime">
            </div>
            <div class="form-group">
                <label for="inputAddress">End time</label>
                <input type="datetime-local" class="form-control" name="endTime">
            </div>
        </div>
        <div class="form-group">
            <label for="inputAddress">Address</label>
            <input type="text" class="form-control" id="inputAddress" placeholder="1-я Удица 3 дом" name="address">
        </div>
        <div class="form-group">
            <label for="inputPrice">Price</label>
            <input type="number" class="form-control" id="inputPrice" name="price" required min="1">
        </div>
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="inputDescription">Description</label>
                <input type="text" class="form-control" id="inputDescription" name="description">
            </div>
        </div>
        <input type="hidden" name="command" value="create_order">
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>
</body>
<script src="<c:url value="/js/common.js" />"></script>
</html>
</fmt:bundle>