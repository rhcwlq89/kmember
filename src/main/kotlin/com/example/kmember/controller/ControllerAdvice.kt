package com.example.kmember.controller

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): String {
        e.printStackTrace()
        return e.message ?: "Unknown Error"
    }
}