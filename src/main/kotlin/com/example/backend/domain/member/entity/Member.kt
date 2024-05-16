package com.example.backend.domain.member.entity

import com.example.backend.domain.common.entity.BaseEntity
import com.example.backend.domain.common.type.MemberType
import com.example.backend.domain.member.dto.SignUpRequest
import jakarta.persistence.Entity
import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.crypto.password.PasswordEncoder
import java.time.LocalDate
import java.util.UUID

@Entity
class Member (

    @Column(nullable = false, unique = true)
    val account: String,

    @Column(nullable = false)
    var password: String,

    var name: String? = null,
    var age: Int? = null,

//    @Column
//    val email: String? = null,

    @Enumerated(EnumType.STRING)
    val type: MemberType = MemberType.ROLE_USER,

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null
): BaseEntity() {


    companion object {
        fun from(request: SignUpRequest, encoder: PasswordEncoder): Member {
            return Member(
                account = request.account!!,
                password = encoder.encode(request.password),
                name = request.name,
                age = request.age
            )
        }
    }


}
