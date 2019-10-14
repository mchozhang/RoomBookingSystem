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
            <h1>My Bookings</h1>
        </div>

        <div>
            <a style="color: indianred" id="error-message"></a>
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
                    <td>
                        <c:choose>
                            <c:when test='${user.getRole().equals("staff")}'>
                                <c:if test='${booking.getStatus().equals("To be confirmed")}'>
                                    <button class="btn btn-primary" onclick="confirm(${booking.getId()})">Confirm
                                    </button>
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <c:if test='${!booking.hasStarted()}'>
                                    <button class="btn btn-primary" data-toggle="modal" data-target="#editModal"
                                            onclick="editModalShow('${booking.getId()}', '${booking.getDateRange()}')">Edit
                                    </button>
                                </c:if>
                                <c:if test='${!booking.hasStarted()}'>
                                    <button class="btn btn-danger" onclick="remove(${booking.getId()})">Delete</button>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- edit modal -->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Edit Booking</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div style="width: 100%; margin-top: 20px">
                            <label>Date Range</label>
                            <input type="text" id="date-picker" name="date-range" style="width: 230px"/>
                        </div>
                        <div>
                            <a style="color: indianred" id="error-message-modal"></a>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button onclick="update()" class="btn btn-primary">Add</button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>

</template:generic>

<script type="text/javascript">
    function confirm(id) {
        let options = {
            url: '/bookingsServlet',
            type: 'post',
            data: 'action=confirm&id=' + id,
            success: function (responseText) {
                if (responseText === 'success') {
                    window.location.href = '/bookingsServlet';
                } else {
                    $('#error-message').text(responseText);
                }
            },
            error: function (responseText) {
                console.log(responseText);
            }
        };

        $.ajax(options);
    }

    let today = new Date();
    let dd = today.getDate();
    let mm = today.getMonth() + 1; //January is 0!
    let yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd
    }
    if (mm < 10) {
        mm = '0' + mm
    }
    today = yyyy + '-' + mm + '-' + dd;
    let startDate, endDate;
    let selectedId;
    $('#date-picker').daterangepicker({
        minDate: today,
        locale: {
            format: 'YYYY-MM-DD',
        }
    }, function (start, end, label) {
        startDate = start.format('YYYY-MM-DD');
        endDate = end.format('YYYY-MM-DD');
        $('#error-message-modal').text('');
    });

    function editModalShow(id, dateRange) {
        selectedId = id;
        $('#date-picker').val(dateRange);
        $('#error-message-modal').text('');
    }

    function update() {
        let options = {
            url: '/bookingsServlet',
            type: 'post',
            data: 'action=edit&id=' + selectedId + '&sd=' + startDate + '&ed=' + endDate,
            success: function (responseText) {
                console.log(responseText);
                if (responseText === 'success') {
                    window.location.href = '/bookingsServlet';
                } else {
                    $('#error-message-modal').text(responseText);
                }
            },
        };

        $.ajax(options);
    }

    function remove(id) {
        let options = {
            url: '/bookingsServlet',
            type: 'post',
            data: 'action=delete&id=' + id,
            success: function (responseText) {
                console.log(responseText);
                if (responseText === 'success') {
                    window.location.href = '/bookingsServlet';
                } else {
                    $('#error-message').text(responseText);
                }
            },
        };

        $.ajax(options);
    }
</script>

