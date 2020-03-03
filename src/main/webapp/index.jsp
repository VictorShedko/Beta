<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head><title>Index</title>
    <script type = "text/javascript" >
        function preventBack(){window.history.forward();}
        setTimeout("preventBack()", 0);
        window.onunload=function(){null};
    </script>
</head>
<body>


<c:if test="${role.value() == 'default'}">
<jsp:forward page="/jsp/common/login.jsp"/>
</c:if>
<c:if test="${role.value() != 'default'}">
    <jsp:forward page="cleaning?command=to_user_menu"/>
</c:if>
</body></html>