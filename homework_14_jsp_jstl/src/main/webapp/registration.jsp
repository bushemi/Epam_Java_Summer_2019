<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="WEB-INF/tag/registrationNewUser.tld" prefix="message" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Registration</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          type="text/css">
    <style>
        .warning {
            color: red;
            font-size: 15px;
        }
        
        .redBorder {
            border: 3px solid red;
        }
    </style>
</head>
<body>

<h1><message:registrationMessage/></h1>
<form class="col-6" action="/users" method="post">
    <c:if test="${not empty sessionScope.login}">
        <c:set var="loginMessage" value="${sessionScope.login}"/>
        <c:set var="loginClass" value="redBorder"/>
    </c:if>
    <c:if test="${not empty sessionScope.password}">
        <c:set var="passwordMessage" value="${sessionScope.password}"/>
        <c:set var="passwordClass" value="redBorder"/>
    </c:if>
    <c:if test="${not empty sessionScope.firstName}">
        <c:set var="firstNameMessage" value="${sessionScope.firstName}"/>
        <c:set var="firstNameClass" value="redBorder"/>
    </c:if>
    <c:if test="${not empty sessionScope.lastName}">
        <c:set var="lastNameMessage" value="${sessionScope.lastName}"/>
        <c:set var="lastNameClass" value="redBorder"/>
    </c:if>
    <c:if test="${not empty sessionScope.age}">
        <c:set var="ageMessage" value="${sessionScope.age}"/>
        <c:set var="ageClass" value="redBorder"/>
    </c:if>
    <div class="form-group">
        <label for="registrationLogin">Логин</label>
        <input name="login" type="text" class="form-control ${loginClass}" id="registrationLogin"
               placeholder="Введите логин">
        <label class="warning">${loginMessage}</label>
    </div>
    <div class="form-group">
        <label for="registrationPassword">Пароль</label>
        <input name="password" type="password" class="form-control ${passwordClass}" id="registrationPassword"
               placeholder="Пароль">
        <label class="warning">${passwordMessage}</label>
    </div>
    <div class="form-group">
        <label for="registrationFirstName">Имя</label>
        <input name="firstName" type="text" class="form-control ${firstNameClass}" id="registrationFirstName"
               placeholder="Имя">
        <label class="warning">${firstNameMessage}</label>
    </div>
    <div class="form-group">
        <label for="registrationLastName">Фамилия</label>
        <input name="lastName" type="text" class="form-control ${lastNameClass}" id="registrationLastName"
               placeholder="Фамилия">
        <label class="warning">${lastNameMessage}</label>
    </div>
    <div class="form-group">
        <label for="registrationAge">Возраст</label>
        <input name="age" type="number" class="form-control ${ageClass}" id="registrationAge"
               placeholder="Возраст">
        <label class="warning">${ageMessage}</label>
    </div>
    <button type="submit" class="btn btn-primary">Регистрация</button>
</form>

</body>
</html>
