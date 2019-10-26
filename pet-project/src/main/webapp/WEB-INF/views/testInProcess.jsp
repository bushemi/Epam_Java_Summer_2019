<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Test In Process</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script>
      <c:if test="${not empty sessionScope.finalTime}">
      let countDownDate = new Date(${sessionScope.finalTime}).getTime();
      
      let x = setInterval(function () {
        
        let now = new Date().getTime();
        
        let distance = countDownDate - now;
        
        let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        let seconds = Math.floor((distance % (1000 * 60)) / 1000);
        
        document.getElementById("timer").innerHTML = "Осталось времени: " + minutes + "m " + seconds + "s ";
        if (distance < 0) {
          clearInterval(x);
          document.getElementById("timer").innerHTML = "Время кончилось!";
        }
      }, 1000);
      </c:if>
      
      function onSubmit() {
        const elements = document.getElementsByClassName("answer");
        let checkedAnswers = [];
        for (let i = 0; i < elements.length; i++) {
          const element = elements[i];
          if (element.checked) {
            checkedAnswers.push(element.name);
          }
        }
        $.ajax({
          url: "/testInProcess",
          type: "post",
          data: JSON.stringify(checkedAnswers),
          contentType: "application/json; charset=utf-8",
          dataType: "json",
          success: function (data) {
            console.log("data", data);
            if (data.redirect) {
              if (window.location.href.endsWith(data.redirect)) {
                window.location.replace(window.location.href);
              } else {
                window.location.href = data.redirect;
              }
            }
          }
        });
      }
      
      function toMainMenu() {
        window.location.href = "navigation";
      }
    </script>
    <style>
        .timerClass {
            height: 10%;
            font-size: 20px;
            color: red;
            border: 3px solid black;
        }
        
        #question {
            height: 30%;
            width: 30%;
        }
    </style>
</head>
<body>
<h1>Прохождение теста</h1>
<br/>
<div class="timerClass">
    <p id="timer"></p>
</div>
<div>
    <button onclick="toMainMenu()">Закончить тест!</button>
</div>
<c:if test="${not empty sessionScope.question}">
    <div>
        <div>
            <label for="question">Вопрос:</label>
            <textarea disabled id="question"
                      maxlength="255">${sessionScope.question.mainText}</textarea>
        </div>
        <c:forEach items="${sessionScope.question.options}" var="option">
            <div>
                <label for="option-${option.id}">${option.mainText}</label>
                <input class="answer" id="option-${option.id}" name="${option.id}"
                       type="checkbox"/>
            </div>
        </c:forEach>
        <button id="submitButton" type="submit" class="btn btn-primary" onclick="onSubmit()">Подтвердить</button>
    </div>
</c:if>
</body>
</html>
