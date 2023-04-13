package mahdi.iranmanesh.filimoanimation

object AndroidUtilities {

    var density = 1

    fun dp(value: Int): Int {
        return if (value == 0) {
            0
        } else Math.ceil((density * value).toDouble()).toInt()
    }
}