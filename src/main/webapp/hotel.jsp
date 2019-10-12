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

            <div style="margin-top: 20px">
                <h2>Room Catalogues</h2>
            </div>
            <c:forEach items="${catalogues}" var="catalogue">
                <div class="card" style="margin-top: 20px">
                    <div class="card-body">
                        <h5 class="card-title">${catalogue.getName()}</h5>
                        <p class="card-text">${catalogue.getDescription()}</p>
                        <p class="card-text">price: $${catalogue.getPrice()}</p>

                        <button class="btn btn-primary" onclick="bookClick(${catalogue.getId()})">Book</button>
                    </div>
                </div>
            </c:forEach>
        </div>
    </jsp:body>

</template:generic>

<script type="text/javascript">
    function bookClick(catalogueId) {
        window.location.href = '/bookServlet?id=' + catalogueId;
    }
</script>

