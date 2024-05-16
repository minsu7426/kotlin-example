package com.example.backend.domain.member.api

import com.example.backend.global.common.ResCode
import com.example.backend.global.error.exception.BusinessException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberApi {

    @GetMapping("/test")
    fun test() :String {
        println("MemberApi.test")
        throw BusinessException(ResCode.BAD_REQUEST)
        return "memberTest"
    }
}