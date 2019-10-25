<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Hello</title>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          type="text/css">
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <style>
        .warning {
            color: red;
            font-size: 15px;
        }
        
        .redBorder {
            border: 3px solid red;
        }
    </style>
    <script>
      function onNewUserChange(e) {
        let mainTitle = document.getElementById("mainTitle");
        let submitButton = document.getElementById("submitButton");
        mainTitle.innerText = e.checked ? "Регистрация" : "Авторизация";
        submitButton.innerText = e.checked ? "Зарегистрироваться" : "Войти";
        e.value = e.checked;
      }
      
      function sendUserInfo() {
        console.log("sendUserInfo");
        const isNewUser = document.getElementById("newUser").checked;
        const login = document.getElementById("registrationLogin").value;
        const password = document.getElementById("registrationPassword").value;
        const data = {
          login: login,
          password: password
        };
        const url = isNewUser ? "/users" : "/authentication";
        console.log("url + data", url, data);
        $.ajax({
          url: url,
          type: "post",
          data: JSON.stringify(data),
          contentType: 'application/json',
          dataType: "JSON",
          success: function (data) {
            console.log("data", data);
            if (data.redirect) {
              window.location.href = data.redirect;
            }
          }
        });
      }
    </script>
</head>
<body>

<div>
    <h1 id="mainTitle">Авторизация</h1>
</div>
<br/>
<div>
    <div class="form-group">
        <label for="newUser">Новый пользователь?</label>
        <input name="isNewUser" id="newUser" type="checkbox" onchange="onNewUserChange(this)"/>
    </div>
    <c:if test="${not empty sessionScope.loginError}">
        <c:set var="loginMessage" value="${sessionScope.loginError}"/>
        <c:set var="loginClass" value="redBorder"/>
        <%
            session.setAttribute("loginError", null);
        %>
    </c:if>
    <c:if test="${not empty sessionScope.passwordError}">
        <c:set var="passwordMessage" value="${sessionScope.passwordError}"/>
        <c:set var="passwordClass" value="redBorder"/>
        <%
            session.setAttribute("passwordError", null);
        %>
    </c:if>
    <div class="form-group">
        <label for="registrationLogin">Логин</label>
        <input name="login" type="text" class="form-control ${loginClass}" id="registrationLogin"
               placeholder="Введите логин"/>
        <label class="warning">${loginMessage}</label>
    </div>
    <div class="form-group">
        <label for="registrationPassword">Пароль</label>
        <input name="password" type="password" class="form-control ${passwordClass}" id="registrationPassword"
               placeholder="Пароль"/>
        <label class="warning">${passwordMessage}</label>
    </div>
    <button id="submitButton" type="submit" class="btn btn-primary" onclick="sendUserInfo()">Войти</button>
</div>

</body>
</html>

