********************************Page Object Model***********************************

Page Object Model -  это своего рода хелпер, но со своими правилами, в смысле оформления. Понятно, у нас были
API хелперы, хелперы БД, которые описывали повторяющеся поля. Это всё ясно. Но как быть с целым сайтом, где
большое количество страниц и если мы создадим общий хелпер и начнём туда кидать все наши элементы, получится
какая-то каша и мы запутаемся. А в Page Object Model можно делать хелперы, например для каждой страницы
тестируемого нами сайта. 
Page Object Model - это даже не просто хелпер, а целый патерн описания интерфейса, или договорённость,
как создавать эти самые хелперы (помощники) для UI тестов.


Чтобы вынести в отдельное поле (в константу) необходимо выделить необходимый повторяющийся в нескольких строчках
(или даже классах) код, клик правой клавишей мыши (ПКМ) -- Refactor -- Introduce Field (Ctrl + Alt + F) --
выбираем или печатаем сами как будет сокращённо в одно слово называться наш повторяющийся код
Если мы в процессе поняли, что хотим переименовать нашу же переменную, то клик на ПКМ -- Refactor -- Rename и
IDE сама всё везде подправит
Если же мы в процессе поняли, что хотим вынести наш повторяющийся код в отдельный метод, то клик на ПКМ -- Refactor --
Extract Method
