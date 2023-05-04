package dev.hawier.genpass.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast


open class PasswordGenerator {
    var password: String = "password"
    var length: Int = 16
    var uppercase: Boolean = true
    var numbers: Boolean = true
    var symbols: Boolean = true


    init {
        this.generatePassword()
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
    open fun copyTextToClipboard(context: Context) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("password", this.password)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Password copied to clipboard", Toast.LENGTH_SHORT).show()
    }
}
