package com.example.backend.domain.common.api

import com.example.backend.domain.common.service.SignService
import com.example.backend.domain.member.dto.SignInRequest
import com.example.backend.domain.member.dto.SignInResponse
import com.example.backend.domain.member.dto.SignUpRequest
import com.example.backend.domain.member.dto.SignupResponse
import com.example.backend.global.common.ResCode
import com.example.backend.global.error.exception.BusinessException
import jakarta.validation.Valid
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/common/")
class CommonApi(
    private val signService: SignService
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody @Valid request: SignUpRequest): ResponseEntity<SignupResponse> {
        println("CommonApi.sign-up")
        return ResponseEntity.ok().body(signService.registMember(request))
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<SignInResponse> {
        println("CommonApi.sign-in")
        return ResponseEntity.ok().body(signService.signIn(request))
    }
}