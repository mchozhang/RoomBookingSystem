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
                    <button class="btn btn-primary btn-sm" onclick="addCatalogues(${hotel.getId()})"
                            data-toggle="modal" data-target="#add-catalogue-modal">Add
                    </button>
                </h2>
            </div>
            <c:forEach items="${catalogues}" var="catalogue" varStatus="loop">
                <div class="card" style="margin-top: 20px">
                    <div class="card-body">
                        <h5 class="card-title">${catalogue.getName()}</h5>
                        <p class="card-text" id="cat-des-${catalogue.getId()}">${catalogue.getDescription()}</p>
                        <p class="card-text">price: $${catalogue.getPrice()}</p>

                        <div class="btn-group" role="group" aria-label="Basic example">
                            <form action="/hotelServlet?id=${hotel.getId()}" method="post">
                                <input name="deleteId" value="${catalogue.getId()}" hidden>
                                <button class="btn btn-primary"
                                        type="button"
                                        onclick="editCatalogue('${catalogue.getId()}', '${catalogue.getName()}', '${catalogue.getPrice()}', '${catalogue.getRoomNumberStr()}')"
                                        data-toggle="modal"
                                        data-target="#edit-catalogue-modal">
                                    Edit
                                </button>
                                <button type="submit" class="btn btn-danger">Remove</button>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- service modal -->
        <div class="modal fade" id="serviceModal" tabindex="-1" role="dialog" aria-labelledby="serviceModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="/hotelServlet?id=${hotel.getId()}" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="serviceModalLabel">Services</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <input type="text" id="service-input" value="${serviceStr}" data-role="tagsinput"
                                   name="serviceStr"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Add</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- add catalogue modal -->
        <div class="modal fade" id="add-catalogue-modal" tabindex="-1" role="dialog"
             aria-labelledby="add-catalogue-modal-label"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="/hotelServlet?id=${hotel.getId()}" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="add-catalogue-modal-label">Room Catalogues</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="add-catalogue-name">Catalogue Name</label>
                                <input type="text" class="form-control" id="add-catalogue-name" name="addName">
                            </div>
                            <div class="form-group">
                                <label for="add-catalogue-des">Description</label>
                                <textarea class="form-control" id="add-catalogue-des" rows="2"
                                          name="addDescription"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="add-catalogue-price">Price</label>
                                <input type="number" class="form-control" id="add-catalogue-price" name="addPrice">
                            </div>

                                <%--room edit--%>
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <label>Rooms</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-info" id="add-rooms"
                                                onclick="addTableAddRow()">
                                            <i class="fa fa-plus"></i>Add Room
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-bordered room-table" id="add-table">
                                <thead>
                                <tr>
                                    <th>Room Number</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Add Catalogue</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- edit catalogue modal -->
        <div class="modal fade" id="edit-catalogue-modal" tabindex="-1" role="dialog"
             aria-labelledby="edit-catalogue-modal-label"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="/hotelServlet?id=${hotel.getId()}" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="edit-catalogue-modal-label">Room Catalogues</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <input id="edit-id" name="editId" hidden>
                            <div class="form-group">
                                <label for="edit-catalogue-name">Catalogue Name</label>
                                <input type="text" class="form-control" id="edit-catalogue-name" name="editName">
                            </div>
                            <div class="form-group">
                                <label for="edit-catalogue-des">Description</label>
                                <textarea class="form-control" id="edit-catalogue-des" rows="2"
                                          name="editDescription"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="edit-catalogue-price">Price</label>
                                <input type="number" class="form-control" id="edit-catalogue-price" name="editPrice">
                            </div>

                            <%--room edit--%>
                            <div class="table-title">
                                <div class="row">
                                    <div class="col-sm-8">
                                        <label>Rooms</label>
                                    </div>
                                    <div class="col-sm-4">
                                        <button type="button" class="btn btn-info" id="edit-rooms"
                                                onclick="editTableAddRow()">
                                            <i class="fa fa-plus"></i>Add Room
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <table class="table table-bordered room-table" id="edit-table">
                                <thead>
                                <tr>
                                    <th>Room Number</th>
                                    <th>Actions</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>

</template:generic>

<script type="text/javascript">

    function editService(hotelId) {
        $('#service-input').tagsinput('removeAll');
        $('#service-input').tagsinput('add', '${serviceStr}');
    }

    function addCatalogues(hotelId) {

    }
    // function editCatalogue(id, name, price) {

    function editCatalogue(id, name, price, rooms) {
        $('#edit-table tbody tr').remove();
        $('#edit-id').val(id);
        $('#edit-catalogue-name').val(name);
        let des = $('#cat-des-' + id).text();
        $('#edit-catalogue-des').val(des);
        $('#edit-catalogue-price').val(price);

        if( rooms === '') {
            return;
        }

        let numbers = rooms.split(',');

        for (number of numbers) {
            addRoom("editRooms", "edit-table", number);
        }
    }

    // Append table with add row form on add new button click
    function addTableAddRow() {
        addRoom("addRooms", "add-table", "");
    }

    function editTableAddRow() {
        addRoom("editRooms", "edit-table", "");
    }

    function addRoom(name, table, value) {
        let actions = '<a class="delete" title="Delete" data-toggle="tooltip"><i class="fas fa-trash"></i></a>';
        let row = '<tr>' +
            '<td><input type="text" class="form-control" name="' + name + '" value="' + value + '"></td>' +
            '<td>' + actions + '</td>' +
            '</tr>';
        $("#" + table).append(row);
    }

    // Delete row on delete button click
    $(document).on("click", ".delete", function () {
        $(this).parents("tr").remove();
    });
</script>

