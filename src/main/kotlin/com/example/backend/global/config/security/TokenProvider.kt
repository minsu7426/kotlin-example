package com.example.backend.global.config.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Service
import java.sql.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.spec.SecretKeySpec

@PropertySource("classpath:application.yml")
@Service
class TokenProvider(
    @Value("\${jwt.secret-key}")
    private val secretKey: String,
    @Value("\${jwt.access.expiration}")
    private val accessTokenValidityInSeconds: Long,
    @Value("\${jwt.refresh.expiration}")
    private val refreshTokenValidityInSeconds: Long,
    @Value("\${jwt.access.header}")
    private val accessHeader: String,
    @Value("\${jwt.refresh.header}")
    private val refreshHeader: String
) {

    private val ID_CLAIM = "id"
    private val ROLES_CLAIM = "roles"
    private val ACCESS_TOKEN_SUBJECT = "AccessToken";
    private val REFRESH_TOKEN_SUBJECT = "RefreshToken";
    private val BEARER = "Bearer ";

    fun createAccessToken(id: String, role: String): String {
        val claims = Jwts.claims()
        claims[ID_CLAIM] = id
        claims[ROLES_CLAIM] = role

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(accessTokenValidityInSeconds, ChronoUnit.SECONDS)))
            .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS512.jcaName))
            .compact()
    }

    fun createRefreshToken(): String = Jwts.builder()
            .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
            .setExpiration(Date.from(Instant.now().plus(refreshTokenValidityInSeconds, ChronoUnit.SECONDS)))
            .signWith(SecretKeySpec(secretKey.toByteArray(), SignatureAlgorithm.HS512.jcaName))
            .compact()


}