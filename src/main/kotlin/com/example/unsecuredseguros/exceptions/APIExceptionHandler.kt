package com.example.unsecuredseguros.exceptions

import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.Exception


@ControllerAdvice
class APIExceptionHandler {

    @ExceptionHandler(
        IllegalArgumentException::class,
        NumberFormatException::class,
        ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleBadRequest(request: HttpServletRequest, e: Exception): ErrorForCustomers{

        return ErrorForCustomers(e.message!!, request.requestURI)
    }


    @ExceptionHandler(
        NotFoundException::class
    )
    @ResponseStatus
    @ResponseBody
    fun handleNotFound(request: HttpServletRequest, e: Exception): ErrorForCustomers{

        return ErrorForCustomers(e.message!!, request.requestURI)
    }


    @ExceptionHandler(
        NoSuchElementException::class
    )
    @ResponseStatus
    @ResponseBody
    fun handleNoSuchElement(request: HttpServletRequest, e: Exception): ErrorForCustomers{

        return ErrorForCustomers(e.message!!, request.requestURI)
    }
}