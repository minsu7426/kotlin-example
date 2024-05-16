package com.example.backend.domain.member.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import java.util.UUID

@Entity
class MemberRefreshToken(
    @MapsId
    @OneToOne
    @JoinColumn(name = "member_id")
    val member: Member,
    private var refreshToken: String
) {
    @Id
    val memberId: UUID? = null

    fun updateRefreshToken(refreshToken: String) {
        this.refreshToken = refreshToken
    }
}