<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 25.01.2020
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}"/>

<fmt:bundle basename="pagecontent">


    <html>
    <head>
        <title><fmt:message key="creditForm.title"/></title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
    <div class="container">
        <div class="row">
            <form action="cleaning" method="GET" class="needs-validation" novalidate>
                <div class="form-row">
                    <div class="form-group">
                        <label for="creditSum"><fmt:message key="creditForm.price.lable"/></label>
                        <input type="hidden" name="command" value="credit_account">
                        <input type="number" class="form-control" id="creditSum" name="creditSum" required min="1"
                               max="1000">
                        <button type="submit" class="btn btn-primary"><fmt:message
                                key="creditForm.button.text"/></button>
                    </div>
                </div>
            </form>
        </div>
        <div class="row">
            <c:out value="${feedback}"/>
        </div>
    </div>

    </body>
    <script src="<c:url value="/js/common.js" />"></script>
    </html>
</fmt:bundle>