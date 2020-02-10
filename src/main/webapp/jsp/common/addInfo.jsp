<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 26.01.2020
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="pagecontent">

<html>
<head>
    <title><fmt:message key="userList.title"/></title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>
<div class="col-4">
    <form action="cleaning" method="POST" enctype="multipart/form-data">
        <div class="input-group">

            <div class="custom-file">
                <input type="file" class="custom-file-input" name="file"/>
                <label class="custom-file-label"><fmt:message key="userList.avatar.label.text"/></label>
            </div>
            <div class="input-group-prepend">
                <input type="hidden" name="command" value="upload_photo"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="userList.avatar.bytton.text"/></button>
            </div>
        </div>

    </form>
    <c:if test="${role.value() == 'executor'}">
    <form action="cleaning" method="POST" enctype="multipart/form-data">
        <div class="input-group">
            <div class="custom-file">
                <input type="file" class="custom-file-input" name="file"/>
                <label class="custom-file-label"><fmt:message key="userList.document.label.text"/></label>
            </div>
            <div class="input-group-prepend">
                <input type="hidden" name="command" value="upload_document"/>
                <button type="submit" class="btn btn-primary"><fmt:message key="userList.document.bytton.text"/></button>
            </div>
        </div>

    </form>
    </c:if>
</div>
</body>
</html>
</fmt:bundle>