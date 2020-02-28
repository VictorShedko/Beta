<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 24.02.2020
  Time: 16:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${locale}"/>
<fmt:bundle basename="pagecontent">
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <link rel="stylesheet" href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <table class="table table-striped" id="documents">
            <thead>
            </thead>
            <tbody>
            <c:forEach items="${documentList}" var="item" varStatus="currentNumber">

                <c:if test="${item.adminName == null}">
                    <tr>
                        <td class="table-dark"><c:out value="${currentNumber.count}"/></td>
                        <td class="table-dark"><img alt="ups" src="<c:url value="${item.file}"/>"/></td>
                        <td class="table-dark"></td>
                    </tr>
                </c:if>
                <c:if test="${item.adminName != null}">
                    <tr>
                        <td class="table-light"><c:out value="${currentNumber.count}"/></td>
                        <td class="table-light"><img src="<c:url value="${item.file}"/>"/></td>
                        <td class="table-light">${item.adminName}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>

</div>
</body>
<script>
    $(document).ready(function(){
        $('#documents').dataTable();
    });
</script>
</html>
</fmt:bundle>