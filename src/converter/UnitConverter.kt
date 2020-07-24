package converter

object UnitConverter {
    fun convert(value: Double, from: Unit, to: Unit): Double {
        if (from::class != to::class || from is UnknownUnit) {
            throw InvalidUnitException("Conversion from ${from.plural} to ${to.plural} is impossible")
        }

        val baseValue = from.convertToBase(value)
        return to.convertFromBase(baseValue)
    }

    fun getUnitByName(name: String): Unit {
        return when (name.toLowerCase()) {
            "m", "meter", "meters" -> DistanceUnit.METER
            "km", "kilometer", "kilometers" -> DistanceUnit.KILOMETER
            "cm", "centimeter", "centimeters" -> DistanceUnit.CENTIMETER
            "mm", "millimeter", "millimeters" -> DistanceUnit.MILLIMETER
            "mi", "mile", "miles" -> DistanceUnit.MILE
            "yd", "yard", "yards" -> DistanceUnit.YARD
            "ft", "foot", "feet" -> DistanceUnit.FOOT
            "in", "inch", "inches" -> DistanceUnit.INCH

            "g", "gram", "grams" -> WeightUnit.GRAM
            "kg", "kilogram", "kilograms" -> WeightUnit.KILOGRAM
            "mg", "milligram", "milligrams" -> WeightUnit.MILLIGRAM
            "lb", "pound", "pounds" -> WeightUnit.POUND
            "oz", "ounce", "ounces" -> WeightUnit.OUNCE

            "dc", "c", "degree celsius", "degrees celsius", "celsius" -> TemperatureUnit.CELSIUS
            "df", "f", "degree fahrenheit", "degrees fahrenheit", "fahrenheit" -> TemperatureUnit.FAHRENHEIT
            "k", "kelvin", "kelvins" -> TemperatureUnit.KELVIN

            else -> UnknownUnit
        }
    }
}