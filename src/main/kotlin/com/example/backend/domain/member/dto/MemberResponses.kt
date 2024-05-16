package com.example.backend.domain.member.dto

import com.example.backend.domain.common.type.MemberType
import com.example.backend.domain.member.entity.Member
import java.util.UUID

data class SignupResponse(
    val id: UUID,
    val account: String,
    val name: String?,
    val age: Int?
) {
    companion object {
        fun from(member: Member): SignupResponse {
            return SignupResponse(
                id = member.id!!,
                account = member.account,
                name = member.name,
                age = member.age
            )
        }
    }
}

data class SignInResponse(
    val name: String?,
    val type: MemberType,
    val accessToken: String,
    val refreshToken: String
)