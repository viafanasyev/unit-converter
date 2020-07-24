package converter

interface Unit {
    val singular: String
    val plural: String

    fun convertToBase(value: Double): Double
    fun convertFromBase(value: Double): Double
}

class InvalidUnitException(message: String) : Exception(message)

enum class DistanceUnit(
        override val singular: String,
        override val plural: String,
        private val multiplier: Double
) : Unit {

    METER("meter", "meters", 1.0),
    KILOMETER("kilometer", "kilometers", 1000.0),
    CENTIMETER("centimeter", "centimeters", 0.01),
    MILLIMETER("millimeter", "millimeters", 0.001),
    MILE("mile", "miles", 1609.35),
    YARD("yard", "yards", 0.9144),
    FOOT("foot", "feet", 0.3048),
    INCH("inch", "inches", 0.0254);

    override fun convertToBase(value: Double): Double {
        if (value < 0) throw InvalidUnitException("Length shouldn't be negative")
        return value * multiplier
    }
    override fun convertFromBase(value: Double): Double {
        if (value < 0) throw InvalidUnitException("Length shouldn't be negative")
        return value / multiplier
    }
}

enum class WeightUnit(
        override val singular: String,
        override val plural: String,
        private val multiplier: Double
) : Unit {

    GRAM("gram", "grams", 1.0),
    KILOGRAM("kilogram", "kilograms", 1000.0),
    MILLIGRAM("milligram", "milligrams", 0.001),
    POUND("pound", "pounds", 453.592),
    OUNCE("ounce", "ounces", 28.3495);

    override fun convertToBase(value: Double): Double {
        if (value < 0) throw InvalidUnitException("Weight shouldn't be negative")
        return value * multiplier
    }
    override fun convertFromBase(value: Double): Double {
        if (value < 0) throw InvalidUnitException("Weight shouldn't be negative")
        return value / multiplier
    }
}

enum class TemperatureUnit(
        override val singular: String,
        override val plural: String
) : Unit {

    CELSIUS("degree Celsius", "degrees Celsius"),
    FAHRENHEIT("degree Fahrenheit", "degrees Fahrenheit"),
    KELVIN("kelvin", "kelvins");

    override fun convertToBase(value: Double): Double {
        return when (this) {
            CELSIUS -> value
            FAHRENHEIT -> (value - 32) * 5 / 9
            KELVIN -> value - 273.15
        }
    }

    override fun convertFromBase(value: Double): Double {
        return when(this) {
            CELSIUS -> value
            FAHRENHEIT -> value * 9 / 5 + 32
            KELVIN -> value + 273.15
        }
    }
}

object UnknownUnit : Unit {
    override val singular = "???"
    override val plural = "???"

    override fun convertToBase(value: Double): Double {
        throw InvalidUnitException("Unknown unit type")
    }

    override fun convertFromBase(value: Double): Double {
        throw InvalidUnitException("Unknown unit type")
    }
}