import Entities.*

class ConsoleUI() {
    var income: Int = 0
    lateinit var currentUser: User
    lateinit var currentOrder: Order
    val commandExecutor: CommandExecutor = CommandExecutor()
    var users: MutableList<User> = mutableListOf()
    var dishes: MutableList<Dish> = mutableListOf()
    var reviews: MutableList<Review> = mutableListOf()
    var finishedOrders: MutableList<Order> = mutableListOf()

    fun showStartMenu() {
        println("Выполнить вход как:")
        println("   1. Клиент")
        println("   2. Администратор")
        println("   3. Выйти")
        print("Введите код доступа: ")
    }

    fun showLoginMenu() {
        println("Войдите в аккаунт или зарегистрируйтесь:")
        println("   1. Войдите в аккаунт")
        println("   2. Создать учетную запись\n")
        print("Введите код: ")
    }

    fun showAdminOptions() {
        println("Администратор. Выберите вариант:")
        println("   1. Показать информацию о блюдах")
        println("   2. Добавить блюдо")
        println("   3. Сменить блюдо")
        println("   4. Удалить блюдо")
        println("   5. Проверить доходы")
        println("   6. Получить статистику")
        println("   7. Выход")
        print("Введите код: ")
    }

    fun showVisitorOptions() {
        println("Выберите вариант:")
        println("   1. Сделать заказ")
        println("   2. Изменить заказ")
        println("   3. Отменить заказ")
        println("   4. Проверить заказы")
        println("   5. Оплатить заказ")
        println("   6. Пополнить кошелек")
        println("   7. Выход")
        print("Введите код: ")
    }

    fun showChangingOption() {
        println("Какую часть вы хотите изменить?")
        println("   1. Изменить сумму")
        println("   2. Изменить цену")
        println("   3. Изменить время приготовления")
        println("   4. Выход")
        print("Введите код: ")
    }

    fun showStatisticMenu() {
        println("Какую статистику вы хотите получить?")
        println("   1. Количество заказов")
        println("   2. Средний рейтинг заказа")
        println("   3. Самое популярное блюдо")
        println("   4. Выход")
        print("Введите код: ")
    }

    fun showOrderOptions() {
        println("Сделайте ваш заказ:")
        println("   1. Добавить блюдо к заказу")
        println("   2. Оформить заказ")
        println("   3. Выход")
        print("Введите код: ")
    }

    fun showReviewOption() {
        println("Хотите оставить отзыв?")
        println("   1. Да")
        println("   2. Нет")
    }
}