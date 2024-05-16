package com.example.backend.global.common

import org.springframework.http.HttpStatus

enum class ResCode (val status: HttpStatus, val message: String){
    //Global Error
    OK (HttpStatus.OK, "success"),
    BAD_REQUEST (HttpStatus.BAD_REQUEST, "400 error"),
    SERVER_ERROR (HttpStatus.INTERNAL_SERVER_ERROR, "500 error"),

    //Sign Error
    DUPLICATED_MEMBER (HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),
    NO_MATCHED_ID_PASSWORD (HttpStatus.BAD_REQUEST, "아이디 또는 패스워드가 일치하지 않습니다.")
}