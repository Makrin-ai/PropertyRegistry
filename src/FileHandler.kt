import java.io.File

object FileHandler {
    private const val FILE_NAME = "realty_data.json"

    // 1.2.9. Сохранение данных в JSON
    fun save(list: List<RealEstate>) {
        val sb = StringBuilder("[\n") //инструмент эффективной сборки больших строк
        list.forEachIndexed { i, r ->
            sb.append("  {\"id\": ${r.id}, \"address\": \"${r.address}\", \"type\": \"${r.type}\", \"area\": ${r.area}, \"price\": ${r.price}}")
            if (i < list.size - 1) sb.append(",")
            sb.append("\n")
        }
        sb.append("]")
        File(FILE_NAME).writeText(sb.toString()) //берем получ текст и записыв в файл на диске
    }

    // 1.2.1. Загрузка данных при запуске
    fun load(): MutableList<RealEstate> {
        val file = File(FILE_NAME)
        val result = mutableListOf<RealEstate>()
        if (!file.exists()) return result

        try {
            val content = file.readText().replace("[", "").replace("]", "").trim()
            if (content.isEmpty()) return result

            content.split("},").forEach {
                val raw = it.replace("{", "").replace("}", "")
                val map = raw.split(",").associate { p -> //associate превращает характер каждого объекта в удобную карту клю-значение
                    val pair = p.split(":")
                    pair[0].trim().replace("\"", "") to pair[1].trim().replace("\"", "")
                }
                result.add(RealEstate(
                    map["id"]!!.toInt(),
                    map["address"]!!,
                    map["type"]!!,
                    map["area"]!!.toDouble(),
                    map["price"]!!.toDouble()
                )) //складываем объекты в изменяемы класс mutablelist
            }
        } catch (e: Exception) {
            // Если файл поврежден, вернем пустой список
        }
        return result
    }
}