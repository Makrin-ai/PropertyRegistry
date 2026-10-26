import java.util.Scanner

// 1.3. Использование коллекции MutableList
var realtyList = mutableListOf<RealEstate>()

fun main() {
    val scanner = Scanner(System.`in`)

    // 1.2.1. Загрузка данных при запуске программы
    realtyList = FileHandler.load()

    var running = true
    while(running) {
        println("\n" + "=".repeat(50))
        println("        СИСТЕМА УЧЕТА ОБЪЕКТОВ НЕДВИЖИМОСТИ БТИ")
        println("=".repeat(50))
        println("1. Показать все объекты")
        println("2. Добавить объект")
        println("3. Редактировать параметр объекта")
        println("4. Удалить объект")
        println("5. Поиск по адресу")
        println("6. Сортировка по параметрам")
        println("7. Статистика (Средняя и Макс. стоимость)")
        println("8. Сохранить данные")
        println("0. Выход")
        print("Ваш выбор: ")

        val choice = scanner.nextLine().trim()

        when (choice) {
            "1" -> showAll()
            "2" -> addRecord(scanner)
            "3" -> editRecord(scanner)
            "4" -> deleteRecord(scanner)
            "5" -> searchByAddress(scanner)
            "6" -> sortRecords(scanner)
            "7" -> calculateStats()
            "8" -> {
                FileHandler.save(realtyList)
                println("→ Успешно сохранено в файл.")
            }
            "0" -> {
                println("Выход из системы...")
                running = false
            }
            else -> println("Ошибка: выберите число 0-8")
        }
    }
}

fun showAll() {
    if (realtyList.isEmpty()) return println("База данных пуста.")
    realtyList.forEach { println("ID: ${it.id} | ${it.address} | Тип: ${it.type} | Площадь: ${it.area} м2 | Цена: ${it.price} руб.") }
}

fun addRecord(scanner: Scanner) {
    try {
        print("Введите ID: "); val id = scanner.nextLine().toInt()
        if (realtyList.any { it.id == id }) return println("Ошибка: объект с таким ID уже есть.")

        print("Введите адрес: "); val addr = scanner.nextLine()
        print("Введите тип объекта: "); val type = scanner.nextLine()
        print("Введите площадь: "); val area = scanner.nextLine().toDouble()
        print("Введите цену: "); val price = scanner.nextLine().toDouble()

        realtyList.add(RealEstate(id, addr, type, area, price))
        println("Объект успешно добавлен.")
    } catch (e: Exception) {
        println("Ошибка ввода: проверьте правильность введенных чисел.")
    }
}

fun editRecord(scanner: Scanner) {
    print("Введите ID для редактирования: ")
    val id = scanner.nextLine().toIntOrNull()
    val item = realtyList.find { it.id == id }

    if (item != null) {
        var editing = true
        while (editing) {
            println("\n--- Редактирование объекта ID: ${item.id} ---")
            println("1. Изменить адрес (сейчас: ${item.address})")
            println("2. Изменить тип (сейчас: ${item.type})")
            println("3. Изменить площадь (сейчас: ${item.area})")
            println("4. Изменить цену (сейчас: ${item.price})")
            println("0. Завершить редактирование этого объекта")
            print("Что изменить? ")

            when (scanner.nextLine().trim()) {
                "1" -> {
                    print("Введите новый адрес: ")
                    item.address = scanner.nextLine()
                    println("Адрес обновлен.")
                }
                "2" -> {
                    print("Введите новый тип: ")
                    item.type = scanner.nextLine()
                    println("Тип обновлен.")
                }
                "3" -> {
                    print("Введите новую площадь: ")
                    val newArea = scanner.nextLine().toDoubleOrNull()
                    if (newArea != null) {
                        item.area = newArea
                        println("Площадь обновлена.")
                    } else println("Ошибка: введите число.")
                }
                "4" -> {
                    print("Введите новую цену: ")
                    val newPrice = scanner.nextLine().toDoubleOrNull()
                    if (newPrice != null) {
                        item.price = newPrice
                        println("Цена обновлена.")
                    } else println("Ошибка: введите число.")
                }
                "0" -> editing = false
                else -> println("Неверный выбор.")
            }
        }
    } else {
        println("Объект с ID $id не найден.")
    }
}

fun deleteRecord(scanner: Scanner) {
    print("Введите ID для удаления: ")
    val id = scanner.nextLine().toIntOrNull()
    if (realtyList.removeIf { it.id == id }) {
        println("Объект удален.")
    } else {
        println("Объект не найден.")
    }
}

fun searchByAddress(scanner: Scanner) {
    print("Поиск (введите часть адреса): ")
    val query = scanner.nextLine().lowercase()
    val results = realtyList.filter { it.address.lowercase().contains(query) }
    if (results.isEmpty()) println("Ничего не найдено.")
    else results.forEach { println("Найдено: ID ${it.id} - ${it.address}") }
}

//упрощенная версия
//fun sortByArea() {
//    //стандартная функция Kotlin для сортировки
//    realtyList.sortBy { it.area }
//    println("Список отсортирован по площади.")
//    showAll()
//}


//сортировка по всем полям
fun sortRecords(scanner: Scanner) {
    if (realtyList.isEmpty()) return println("Нет данных для сортировки.")

    println("\n--- Выберите поле для сортировки ---")
    println("1. По ID")
    println("2. По адресу (алфавит)")
    println("3. По типу")
    println("4. По площади")
    println("5. По цене")
    print("Ваш выбор: ")

    when (scanner.nextLine().trim()) {
        "1" -> realtyList.sortBy { it.id }
        "2" -> realtyList.sortBy { it.address.lowercase() }
        "3" -> realtyList.sortBy { it.type.lowercase() }
        "4" -> realtyList.sortBy { it.area }
        "5" -> realtyList.sortBy { it.price }
        else -> {
            println("Неверный выбор, сортировка не выполнена.")
            return
        }
    }

    println("Сортировка успешно выполнена!")
    showAll()
}

fun calculateStats() {
    if (realtyList.isEmpty()) return println("Нет данных для анализа.")

    // Получаем список всех цен
    val prices = realtyList.map { it.price }

    println("\n--- Агрегированные показатели (Стоимость) ---")
    println("1. Сумма всех объектов:  ${prices.sum()} руб.")
    println("2. Средняя стоимость:    ${"%.2f".format(prices.average())} руб.")
    println("3. Минимум:              ${prices.minOrNull()} руб.")
    println("4. Максимум:             ${prices.maxOrNull()} руб.")
}