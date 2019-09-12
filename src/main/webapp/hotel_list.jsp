<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template:generic>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div>
            <h1>Hotel</h1>
        </div>

        <table class="table table-striped table-dark">
            <thead>
            <tr>
                <th scope="col">Hotel Name</th>
                <th scope="col">Suburb</th>
                <th scope="col">Address</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hotel" items="${hotels}">
                <tr class='table-row' data-href='/hotelServlet?id=${hotel.getId()}'>
                    <td>${hotel.getName()}</td>
                    <td>${hotel.getSuburb()}</td>
                    <td>${hotel.getAddress()}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>

</template:generic>

<script type="text/javascript">
    $(document).ready(function($) {
        $(".table-row").click(function() {
            window.document.location = $(this).data("href");
        });
    });
</script>

