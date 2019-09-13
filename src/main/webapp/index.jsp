<%@taglib prefix="template" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<template:generic>
    <jsp:attribute name="header">
    </jsp:attribute>

    <jsp:attribute name="footer">
    </jsp:attribute>

    <jsp:body>
        <div style="margin-bottom: 20px; margin-left: 10px">
            <h1>Login</h1>
        </div>
        <div class="col-sm-3">
            <div class="panel panel-default">
                <div>
                    <a style="color: indianred">${errorMessage}</a>
                </div>
                <div class="panel-body">
                    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" id="username-input" name="username"
                                   placeholder="Username">
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-control" id="password-input" name="password"
                                   placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-dark">Log In</button>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>

</template:generic>

<script type="text/javascript">
    function login() {
        let username = $("#username-input").val();
        let password = $("#password-input").val();

        let data = {
            username: username,
            password: password,
        };

        let options = {
            url: "/loginServlet",
            data: data,
            success: function(response){
                console.log(response);
            }
        };

        $.ajax(options);
    }
</script>
