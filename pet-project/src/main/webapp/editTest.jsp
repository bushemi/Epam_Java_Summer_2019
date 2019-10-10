<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add Test</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <style>
        button {
            width: 10%;
            background-color: aquamarine;
        }
        
        .sliderValue {
            width: 3%;
        }
    </style>
    <script>
      function toMainMenu() {
        window.location.href = "navigation";
      }
      
      function onChangeDifficulty(e) {
        console.log(e.value);
        const difficultyValue = document.getElementById("difficultyValue");
        difficultyValue.value = e.value;
      }
      
      function onChangeCompletingTime(e) {
        console.log(e.value);
        const secondsToCompleteValue = document.getElementById("secondsToCompleteValue");
        const number = (e.value) / 60;
        secondsToCompleteValue.value = number + "минут";
      }
      
      function addQuestion() {
        console.log("add question");
      }
      
      function saveTest() {
        const testId = document.getElementById("testId").value;
        const selected = document.getElementById("subjectId").selectedIndex;
        const subjectId = document.getElementById("subjectId").options[selected].value;
        const testName = document.getElementById("testName").value;
        const difficulty = document.getElementById("difficulty").value;
        const secondsToComplete = document.getElementById("secondsToComplete").value;
        const data = {
          id: testId,
          subjectId: subjectId,
          testName: testName,
          difficulty: difficulty,
          secondsToComplete: secondsToComplete
        };
        $.post("/tests", data, function () {
          window.location.href = "editTest";
        });
      }
    </script>
</head>
<body>
<h1>Редактирование теста</h1>
<br/>
<div>
    <button onclick="toMainMenu()">На главное меню</button>
    <button onclick="saveTest()">Сохранить тест</button>
</div>
<br/>
<c:if test="${not empty sessionScope.test}">
    <input hidden id="testId" value="${sessionScope.test.id}"/>
    <div>
        <label for="subjectId">Предмет:</label>
        <select id="subjectId">
            <c:if test="${not empty sessionScope.subjects}">
                <c:forEach items="${sessionScope.subjects}" var="item">
                    <c:choose>
                        <c:when test="${sessionScope.test.subjectId == item.id}">
                            <option value="${item.id}" selected>${item.subjectName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${item.id}">${item.subjectName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
        </select>
    </div>
    <div>
        <label for="testName">Название теста:</label>
        <textarea id="testName" maxlength="255">${sessionScope.test.testName}</textarea>
    </div>
    <div>
        <label for="difficulty">Сложность:</label>
        <input class="sliderValue" id="difficultyValue" disabled value="5"/>
        <input id="difficulty" type="range" min="1" max="10"
               value=${sessionScope.test.difficulty} onchange="onChangeDifficulty(this)"/>
    </div>
    <div>
        <label for="secondsToComplete">Секунд на выполнение:</label>
        <input class="sliderValue" id="secondsToCompleteValue" disabled value=${sessionScope.test.secondsToComplete}/>
        <input id="secondsToComplete" type="range" min="30" max="1800"
               value=${sessionScope.test.secondsToComplete} step="15"
               onchange="onChangeCompletingTime(this)"/>
    </div>
    <div>
        <label for="questionsQuantity">Количество вопросов в тесте:</label>
        <input id="questionsQuantity" value=${sessionScope.test.questionsQuantity}/>
    </div>
    <div>
        <button onclick="addQuestion()">Добавить вопрос</button>
    </div>
</c:if>
</body>
</html>
