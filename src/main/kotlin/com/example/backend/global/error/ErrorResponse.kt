package com.example.backend.global.error

import com.example.backend.global.common.ResCode
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult

data class ErrorResponse (
    val status: Int,
    val message: String,
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val errors: List<CustomFieldError>
){

    companion object {
        fun of(errorCode: ResCode): ErrorResponse {
            return ErrorResponse(errorCode.status.value(), errorCode.message, emptyList())
        }

        fun createBinding(errorCode: ResCode, bindingResult: BindingResult): ErrorResponse {
            return ErrorResponse(errorCode.status.value(), errorCode.message, CustomFieldError.of(bindingResult))
        }
    }
}

data class CustomFieldError(
    val field: String,
    val value: String,
    val reason: String
) {
    companion object {
        fun of(bindingResult: BindingResult): List<CustomFieldError> {
            return bindingResult.fieldErrors.map { error ->
                CustomFieldError(
                    field = error.field,
                    value = error.rejectedValue?.toString() ?: "",
                    reason = error.defaultMessage ?: ""
                )
            }
        }
    }
}