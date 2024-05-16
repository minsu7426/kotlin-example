package com.example.backend.domain.member.repository

import com.example.backend.domain.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface MemberRepository: JpaRepository<Member, UUID> {
    fun findByName(account: String): Member?
    fun findByAccount(account: String): Member?
}