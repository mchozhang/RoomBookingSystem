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
            <h1>${hotel.getName()} - ${catalogue.getName()}</h1>
        </div>

        <div class="container" style="margin-top: 30px;">

            <div>
                <label>${catalogue.getDescription()}</label>
            </div>

            <div style="margin-top: 20px">
                <label>Price: $${catalogue.getPrice()}</label>
            </div>

            <form action="/bookServlet" method="post">
                <div style="width: 100%; margin-top: 20px">
                    <label>Date Range</label>
                    <input type="text" id="date-picker" name="date-range" style="width: 200px"/>
                </div>

                <div style="margin-top: 20px">
                    <label> Available Rooms</label>
                    <table class="table table-bordered room-table" id="table">
                        <thead>
                        <tr>
                            <th>Room Number</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${rooms}" var="room">
                            <tr>
                                <td>${room.getNumber()}</td>
                                <td>
                                    <button class="btn btn-primary" onclick="book(${room.getId()})">Confirm Booking
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </jsp:body>

</template:generic>

<script type="text/javascript">
    let startDate = '${startDate}', endDate = '${endDate}';
    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth()+1; //January is 0!
    let yyyy = today.getFullYear();
    if(dd<10){ dd='0'+dd }
    if(mm<10){ mm='0'+mm }
    today = yyyy +'-'+mm+'-'+ dd;

    $('#date-picker').daterangepicker({
        startDate: startDate,
        endDate: endDate,
        minDate: today,
        locale: {
            format: 'YYYY-MM-DD',
        }
    }, function (start, end, label) {
        startDate = start.format('YYYY-MM-DD');
        endDate = end.format('YYYY-MM-DD');
        window.location.href = '/bookServlet?id=${catalogue.getId()}&sd=' +
            startDate + '&ed=' + endDate;
    });

    function book(roomId) {
        let options = {
            url: '/bookServlet',
            type: 'post',
            data: 'sd=' + startDate + '&ed=' + endDate + '&room=' + roomId + '&id=${catalogue.getId()}',
        };

        $.ajax(options);
    }
</script>

