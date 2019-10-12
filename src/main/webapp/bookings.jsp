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
            <h1>My Bookings</h1>
        </div>

        <table class="table table-striped table-dark" style="margin-top: 30px">
            <thead>
            <tr>
                <c:choose>
                    <c:when test='${user.getRole().equals("staff")}'>
                        <th scope="col">Customer</th>
                    </c:when>
                    <c:otherwise>
                        <th scope="col">Hotel</th>
                    </c:otherwise>
                </c:choose>
                <th scope="col">Catalogue</th>
                <th scope="col">Room</th>
                <th scope="col">Price</th>
                <th scope="col">Date</th>
                <th scope="col">Status</th>
                <th scope="col">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="booking" items="${bookings}">
                <tr class='table-row'>
                    <c:choose>
                        <c:when test='${user.getRole().equals("staff")}'>
                            <td>${booking.getUserFullName()}</td>
                        </c:when>
                        <c:otherwise>
                            <td>${booking.getHotelName()}</td>
                        </c:otherwise>
                    </c:choose>

                    <td>${booking.getCatalogueName()}</td>
                    <td>${booking.getRoomNumber()}</td>
                    <td>${booking.getPrice()}</td>
                    <td>${booking.getDateRange()}</td>
                    <td>${booking.getStatus()}</td>
                    <td></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </jsp:body>

</template:generic>

<script type="text/javascript">

</script>

