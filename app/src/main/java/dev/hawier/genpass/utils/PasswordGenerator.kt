package dev.hawier.genpass.utils

open class PasswordGenerator {
    var password: String = "password"
    var length: Int = 16
    var uppercase: Boolean = true
    var numbers: Boolean = true
    var symbols: Boolean = true


    init {
        generatePassword()
    }
    open fun generatePassword() {
        var tempString = ""
        var chars = "abcdefghijklmnopqrstuvwxyz"
        if (uppercase) chars += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        if (numbers) chars +="0123456789"
        if (symbols) chars += "!@#$%^&*()_+"

        for (i in 0 until length) {
            val random = (chars.indices).random()
            tempString += chars[random]
        }
        this.password = tempString
    }
}
