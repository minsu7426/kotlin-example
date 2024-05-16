package com.example.backend.domain.member.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Length


data class SignUpRequest(
    @field:Length(min = 5, max = 20)
    @field:NotBlank(message = "아이디는 필수입니다.")
    val account: String?,        //아이디
    @field:NotBlank(message = "패스워드는 필수입니다.")
    var password: String?,       //패스워드

    val name: String?,          //이름
    val age: Int?               //나이
)

data class SignInRequest(
    val account: String,        //아이디
    val password: String        //패스워드
)