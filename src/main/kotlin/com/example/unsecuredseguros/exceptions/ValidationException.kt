package com.example.unsecuredseguros.exceptions

class ValidationException(message: String): Exception("ERROR en la validación (400). $message.")