# Система управления заказами в ресторане
----------------------------------------------------------
**Базовые требования**
- [x] Реализовать систему аутентификации для двух типов пользователей: посетителей и администраторов.
- [x] Администратор может добавлять и удалять блюда из меню, а также устанавливать их количество, цену и сложность выполнения (время, которое оно будет готовиться).
- [x] Посетители могут составлять заказ, выбирая блюда из актуального меню.
- [x] Заказы обрабатываются в отдельных потоках, симулируя процесс приготовления.
- [x] Посетители могут добавлять блюда в существующий заказ, пока он находится в обработке.
- [x] Посетители могут отменять заказ до того, как он будет готов.
- [x] Система отображает актуальный статус заказа
- [x] По завершению выполнения заказа посетитель может его оплатить.
- [x] Необходимо сохранять состояния программы: меню, сумму выручки, пользователей в системе, а также то, что вы посчитаете нужным. Реализовавано через csv-файлы.
      
**Дополнительные требования**
- [x] Клиенты могут оставлять отзывы о блюдах после оплаты заказа. Отзывы включают оценку от 1 до 5 и текстовый комментарий.
- [x] Реализован функционал, позволяющий администратору просматривать статистику по заказам (самые популярные блюда, средняя оценка блюд, количество заказов за период) и отзывам.
- [x] Реализованна система приоритетов для обработки заказов (первые заказы, которые были добавлены, будут обработаны в первую очередь).
      
Все решения соблюдают принципы ООП и SOLID,

# Шаблоны проектирования
----------------------------------------------------------
Проект использует различные шаблоны проектирования для обеспечения чистого и поддерживаемого кода:
1) **Паттерн Команда** --> вместо того, чтобы полагаться на многоуровневую структуру switch-case для обработки пользовательских команд, паттерн "Команда" предлагает альтернативный подход, где каждая команда оформлена как отдельный класс. Это позволяет объекту команды самостоятельно определять, к какой именно команде следует направить входящий запрос и в какой форме это следует делать. Этот подход не только упрощает добавление новых команд без необходимости изменения существующего кода управления, но и способствует более чистой архитектуре и легкости в обслуживании системы, делая каждую команду легко настраиваемой и тестируемой в изоляции.
2) **Паттерн Строитель** --> был использован для пошагового создания сложных объектов, таких как пользователь с ролью админа или посетителя, позволяя избежать сложности конструктора с множеством параметров и обеспечивая гибкость в процессе инициализации.


# Функциональность проекта
----------------------------------------------------------
```Kotlin
Выполнить вход как:
   1. Клиент
   2. Администратор
   3. Выйти
Введите код доступа:
```
# Клиент
----------------------------------------------------------
```Kotlin
Войдите в аккаунт или зарегистрируйтесь: ---следующее меню---> Выберите вариант: 
      1. Войдите в аккаунт                                         1. Сделать заказ ------> Сделайте ваш заказ:
      2. Создать учетную запись                                    2. Изменить заказ            1. Добавить блюдо к заказу ------> 1. "Оладьи" с количеством 24. Цена: 120
Введите код:                                                       3. Отменить заказ            2. Оформить заказ                  2. "Гречневая каша" с количеством 50. Цена: 200
                                                                   4. Проверить заказы          3. Выход                           3. "Бефстроганов" с количеством 15. Цена: 550
                                                                   5. Оплатить заказ        Введите код:                           ...
                                                                   6. Пополнить кошелек
                                                                   7. Выход
                                                                Введите код:
```
```Kotlin
 Выберите вариант: 
        1. Сделать заказ  
        2. Изменить заказ  ------> 1. Пользователь User сделал заказ по цене «550 рублей», заказ имеет статус: В процессе... 
        3. Отменить заказ             Выберите заказ для внесения изменений:
                              Примечание: Изменять можно только заказы со статусом «В процессе...», готовые заказы изменить уже нельзя
        4. Проверить заказы         
        5. Оплатить заказ       
        6. Пополнить кошелек
        7. Выход
Введите код:
```
```Kotlin
 Выберите вариант: 
        1. Сделать заказ  
        2. Изменить заказ
        3. Отменить заказ ------>  Пользователь может отменить только заказы со статусом «В процессе...», готовые заказы отменить уже нельзя
        4. Проверить заказы ------> 1. Пользователь User сделал заказ по цене «120 рублей», заказ имеет статус: Finished
                                    2. Пользователь User сделал заказ по цене «550 рублей», заказ имеет статус: В процессе... 
        5. Оплатить заказ           Примечание: данная команда показывает заказы и их статус 
        6. Пополнить кошелек
        7. Выход
Введите код:
```
```Kotlin
 Выберите вариант: 
        1. Сделать заказ  
        2. Изменить заказ  
        3. Отменить заказ           
        4. Проверить заказы         
        5. Оплатить заказ ------>    1. Заказ пользователя 111 готов, к оплате «120 рублей»
                                     2. Заказ пользователя 111 готов, к оплате «550 рублей»    
        6. Пополнить кошелек         Выберите заказ для оплаты:
        7. Выход                          ......
Введите код:                         Хотите оставить отзыв?
                                     1. Да
                                     2. Нет
```
```Kotlin
 Выберите вариант: 
        1. Сделать заказ  
        2. Изменить заказ 
        3. Отменить заказ        
        4. Проверить заказы         
        5. Оплатить заказ       
        6. Пополнить кошелек  ------> Сколько денег нужно внести на депозит?
        7. Выход
Введите код:
```

