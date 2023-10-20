package com.rrc.adev3007.pixel_perfect.the_y_app

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class CreateUserViewModel : ViewModel() {
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var errors by mutableStateOf<Map<String, String>>(emptyMap())

    fun validateEmail(): Map<String, String>{
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")
        val isValid = emailPattern.matches(email)
        val updatedErrors = errors.toMutableMap()

        if (email.isBlank()) {
            updatedErrors["email"] = "Email is required"
        } else if (!isValid) {
            updatedErrors["email"] = "Invalid email"
        } else {
            updatedErrors.remove("email")
        }

        return updatedErrors
    }

    fun validateForm(): Boolean {
        val updatedErrors = errors.toMutableMap()

        updatedErrors.putAll(validateEmail())

        if (password.isBlank()) {
            updatedErrors["password"] = "Password is required"
        }

        if (firstName.isBlank()) {
            updatedErrors["firstName"] = "First name is required"
        }

        if (lastName.isBlank()) {
            updatedErrors["lastName"] = "Last name is required"
        }

        errors = updatedErrors
        return errors.isEmpty()
    }
}