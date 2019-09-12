<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template:generic>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div class="col-sm-3">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form>
                        <div class="form-group">
                            <input type="text" class="form-control" id="username-input" name="username"
                                   placeholder="Username">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password-input" name="password"
                                   placeholder="Password">
                        </div>
                        <button class="btn btn-dark" onclick="login()">Log In</button>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>

</template:generic>

<script type="text/javascript">
    function login() {
        console.log("login");
        let username = $("#username-input").val();
        let password = $("#password-input").val();

        let data = {
            username: username,
            password: password,
        };

        let options = {
            url: "",
            dataType: "json",
            contentType: "application/json",
            type: "post",
            data: data,
        };

        // $.ajax(options)
        //     .done(function (responseText) {
        //         console.log("111");
        //         console.log(responseText);
        //     });
    }
</script>
