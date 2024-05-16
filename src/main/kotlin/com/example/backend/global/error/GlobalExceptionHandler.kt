package com.example.backend.global.error

import com.example.backend.global.common.ResCode
import com.example.backend.global.common.logger
import com.example.backend.global.error.exception.BusinessException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.Exception

@RestControllerAdvice
class GlobalExceptionHandler {

    val log = logger();

    @ExceptionHandler(value = [Exception::class])
    @ResponseBody
    fun commonException(exception: Exception): ResponseEntity<ErrorResponse> {
        log.info("global exception")
        log.error(exception.message);
        val response = ErrorResponse.of(ResCode.SERVER_ERROR)
        return ResponseEntity.status(response.status).body(response)
    }

    @ExceptionHandler(BusinessException::class)
    @ResponseBody
    fun businessException(exception: BusinessException): ResponseEntity<ErrorResponse> {
        log.info("business exception")
        log.error(exception.message)
        val response = ErrorResponse.of(exception.resCode)
        return ResponseEntity.status(response.status).body(response)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    fun methodException(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        log.info("method exception")
        log.error(exception.message)
        val response = ErrorResponse.createBinding(ResCode.BAD_REQUEST, exception.bindingResult)
        return ResponseEntity.status(response.status).body(response)
    }

}