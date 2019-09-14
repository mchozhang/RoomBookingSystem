<%@tag description="template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <%--    <title>${title}</title>--%>
    <title>Hotel Booker</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/home.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap-tagsinput/css/bootstrap-tagsinput.css"
          rel="stylesheet"/>
<<<<<<< HEAD
    <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap-datepicker/css/bootstrap-datepicker.min.css"
          rel="stylesheet"/>
=======
>>>>>>> ef5fa25ec4ac063113172a2f242b810f74ff20f3
</head>
<body>
<div id="page-header">
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Hotel Booker</a>
    </nav>
</div>
<div id="body" style="margin: 50px;">
    <jsp:doBody/>
</div>
<div id="page-footer">
    <jsp:invoke fragment="footer"/>
</div>
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-tagsinput/js/bootstrap-tagsinput.js"></script>
<<<<<<< HEAD
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
=======
>>>>>>> ef5fa25ec4ac063113172a2f242b810f74ff20f3
<script src="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/js/all.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>
</body>
</html>
