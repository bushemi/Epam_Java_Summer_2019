<!DOCTYPE html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Hello Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style>
        body {
            background-color: beige;
        }
        
        div {
            text-align: center
        }
        
        a {
            border: 2px solid black;
            font-size: 25px;
            padding: 5px;
            border-radius: 10px;
            background-color: white;
        }
    </style>
</head>
<body>
<h1>Это стартовая страница</h1>
<div>
    <a href="registration.jsp">Регистрация нового пользователя</a>
</div>
</body>
</html>