Задание:
На входе имеется файл с логами logs.txt в папке homework.

Каждая строка из файла имеет вид:
2018-07-30 11:49:27.661 INFO Module=MIC Operation: Logout  Execution time: 17281 ms

Написать программу, которая будет анализировать этот файл и выводить статистику:
N самых длинных операций для каждого модуля, длительность операции и время, когда операция закончилась. Операции должны быть отсортированы по имени модуля и в порядке убывания времени выполнения.

Возможность сериализовать и десериализовать результаты анализа в/из файл(а). Загружать и показывать результаты анализа логов из файла. Программа должна поддерживать флаги коммандной строки:
-file <path_to_logs_file>
-load <path_to_saved_results>
-view N
-save <path_to_saved_results>

Пример загрузки: java -jar homework.jar -load saved_results.dat
Пример только вывода данных java -jar homework.jar -file logs.txt –view 10
Пример вывода данных и сохранения: 
java -jar homework.jar -file logs.txt –view 10 –save saved_results.dat

Пример вывода данных
Top 5 operations in "logs.txt" file:
ASC Module:
    OperationX 9999 ms, finished at 2018-07-30 11:48:18.907
OperationY 9998 ms, finished at 2018-07-30 10:48:18.905
OperationZ 9997 ms, finished at 2018-07-30 13:48:13.902
OperationN 9996 ms, finished at 2018-07-30 12:48:18.901
OperationK 9995 ms, finished at 2018-07-30 09:48:18.900
CMN Module:
    OperationX 9999 ms, finished at 2017-12-21 14:51:46.12
    ...
...     
