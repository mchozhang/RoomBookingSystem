<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template:generic>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div>
            <h1>${hotel.getName()}</h1>
        </div>

        <div class="container" style="margin-top: 30px;">
            <div>
                <h2>Services</h2>
            </div>
            <ul class="list-group list-group-horizontal">
                <c:forEach items="${services}" var="service">
                    <li class="list-group-item">${service.getName()}</li>
                </c:forEach>
            </ul>
        </div>
    </jsp:body>

</template:generic>

<script type="text/javascript">
    function login() {

    }
</script>

