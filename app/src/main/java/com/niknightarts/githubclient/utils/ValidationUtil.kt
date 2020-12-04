package com.niknightarts.githubclient.utils

import android.util.Patterns
import java.util.regex.Pattern

class ValidationUtil {

    companion object {
        private val namePattern = Pattern.compile("^[A-Za-z]+$")
        private val passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")

        fun validateName(name: String): Boolean {
            return name.isNotEmpty() && namePattern.matcher(name).matches() && name.length <= 20
        }

        fun validateEmail(email: String): Boolean {
            return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun validateDate(date: String): Boolean {
            return date.isNotEmpty() && date.length >= 10 && !(date.contains("D") || date.contains(
                "M",
                date.contains("Y")
            ))
        }

        fun validatePhone(phone: String?): Boolean {
            return !phone.isNullOrEmpty() && Patterns.PHONE.matcher(phone).matches()
        }

        fun validatePasswordConfirmation(password_one: String, password_two: String): Boolean {
            return password_one.isNotEmpty() && password_two.isNotEmpty() && password_one == password_two
        }

        fun validatePassword(password: String): Boolean {
            //todo return password.isNotEmpty() && passwordPattern.matcher(password).matches()
            return password.isNotEmpty() && password.length > 5
        }

        fun validateCode(code: String): Boolean {
            return code.isNotEmpty() && code.length == 4
        }
    }
}
