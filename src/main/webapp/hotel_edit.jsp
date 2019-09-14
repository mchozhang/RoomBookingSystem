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
                <h2>Services
                    <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#serviceModal"
                            onclick="editService(${hotel.getId()})">Edit
                    </button>
                </h2>
            </div>
            <ul class="list-group list-group-horizontal">
                <c:forEach items="${services}" var="service">
                    <li class="list-group-item">${service.getName()}</li>
                </c:forEach>
            </ul>

            <div style="margin-top: 20px">
                <h2>Room Catalogues
                    <button class="btn btn-primary btn-sm" onclick="editCatalogues(${hotel.getId()})" data-toggle="modal" data-target="#catalogueModal">Edit</button>
                </h2>
            </div>
            <c:forEach items="${catalogues}" var="catalogue">
                <div class="card" style="margin-top: 20px">
                    <div class="card-body">
                        <h5 class="card-title">${catalogue.getName()}</h5>
                        <p class="card-text">${catalogue.getDescription()}</p>
                        <p class="card-text">price: $${catalogue.getPrice()}</p>

                        <button class="btn btn-primary" onclick="editCatalogue(${catalogue.getId()})">Edit</button>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- service modal -->
        <div class="modal fade" id="serviceModal" tabindex="-1" role="dialog" aria-labelledby="serviceModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="serviceModalLabel">Services</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        ...
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
=
        <!-- catalogue modal -->
        <div class="modal fade" id="catalogueModal" tabindex="-1" role="dialog" aria-labelledby="catalogueModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="catalogueModalLabel">Room Catalogues</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <input>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>

</template:generic>

<script type="text/javascript">

    function editService(hotelId) {
        console.log("edit service ");
        console.log(hotelId);
    }

    function editCatalogues(hotelId) {
        console.log("edit catalogues ");
        console.log(hotelId);
    }

    function editCatalogue(catalogueId) {
        console.log("book on click " + catalogueId);
        window.location.href = '/bookingServlet?id=' + catalogueId;
    }
</script>

