Создать в вашем проекте два аспекта

Первый применяется к слою сервисов и содержит два Advice - After and Before,
 в которых выводится название функции, список параметров для этой функции и значение, 
 которое эта функция возвращает.
Второй вызывается с помощью аннотации, содержит Advice Around 
и с помощью него необходимо узнать название метода и время ее выполнения.
 Аннотацию поставить над всеми методами контроллера.