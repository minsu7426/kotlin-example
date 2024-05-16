package com.example.backend.global.error.exception

import com.example.backend.global.common.ResCode
import com.example.backend.global.error.ErrorResponse

class BusinessException(
    val resCode: ResCode
) : RuntimeException(resCode.message) {

    fun getErrorCode(): ResCode {
        return resCode
    }
}