package com.example.backend.domain.common.service

import com.example.backend.domain.common.type.MemberType
import com.example.backend.domain.member.dto.SignInRequest
import com.example.backend.domain.member.dto.SignInResponse
import com.example.backend.domain.member.dto.SignUpRequest
import com.example.backend.domain.member.dto.SignupResponse
import com.example.backend.domain.member.entity.Member
import com.example.backend.domain.member.entity.MemberRefreshToken
import com.example.backend.domain.member.repository.MemberRefreshTokenRepository
import com.example.backend.domain.member.repository.MemberRepository
import com.example.backend.global.common.ResCode
import com.example.backend.global.common.logger
import com.example.backend.global.config.security.TokenProvider
import com.example.backend.global.error.exception.BusinessException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SignService(
    private val memberRepository: MemberRepository,
    private val encoder: PasswordEncoder,
    private val tokenProvider: TokenProvider,
    private val memberRefreshTokenRepository: MemberRefreshTokenRepository
) {
    val log = logger()

    fun registMember(request: SignUpRequest): SignupResponse {
        try {
            val member = memberRepository.saveAndFlush(Member.from(request, encoder))
//            var findMember = memberRepository.findByName("name1")
//            findMember?.name = "name2"
            return SignupResponse.from(member)
        } catch (e: Exception) {
            throw BusinessException(ResCode.DUPLICATED_MEMBER)
        }
    }

    fun signIn(request: SignInRequest): SignInResponse {
        val member = memberRepository.findByAccount(request.account)
            ?.takeIf { encoder.matches(request.password, it.password) }
            ?: throw BusinessException(ResCode.NO_MATCHED_ID_PASSWORD)

        val accessToken = tokenProvider.createAccessToken(member.id.toString(), member.type.toString())

        val refreshToken = tokenProvider.createRefreshToken()
        memberRefreshTokenRepository.findByIdOrNull(member.id!!)?.updateRefreshToken(refreshToken)
            ?: memberRefreshTokenRepository.save(MemberRefreshToken(member, refreshToken))
        return SignInResponse(member.name, member.type, accessToken, refreshToken)
    }

}