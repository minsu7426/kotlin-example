package com.example.backend.domain.member.repository

import com.example.backend.domain.member.entity.MemberRefreshToken
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRefreshTokenRepository: JpaRepository<MemberRefreshToken, UUID> {
}