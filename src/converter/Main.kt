package converter

import java.util.*

class ParseException(message: String) : Exception(message)

fun parseConversionInput(line: String): Array<String> {
    val scanner = Scanner(line)
    val result = Array(3) { "" }

    try {
        result[0] = scanner.nextDouble().toString()
    } catch (e: InputMismatchException) {
        throw ParseException("Expected number")
    }

    result[1] = scanner.next().toLowerCase()
    if (result[1] == "degree" || result[1] == "degrees")
        result[1] += " " + scanner.next()

    scanner.next()

    result[2] = scanner.next().toLowerCase()
    if (result[2] == "degree" || result[2] == "degrees")
        result[2] += " " + scanner.next()

    if (scanner.hasNext()) {
        throw ParseException("Input is not empty after parsing")
    }

    return result
}

fun main() {
    while(true) {
        println("Enter what you want to convert (or exit): ")
        val line = readLine()!!
        if (line == "exit") break

        try {
            val input = parseConversionInput(line)
            val value = input[0].toDouble()
            val from = UnitConverter.getUnitByName(input[1])
            val to = UnitConverter.getUnitByName(input[2])
            val result = UnitConverter.convert(value, from, to)
            println(
                    "$value ${if (value == 1.0) from.singular else from.plural} " +
                    "is " +
                    "$result ${if (result == 1.0) to.singular else to.plural}"
            )
        } catch (e: ParseException) {
            println("Parse error")
        } catch (e: InvalidUnitException) {
            println(e.message)
        }
    }
}
