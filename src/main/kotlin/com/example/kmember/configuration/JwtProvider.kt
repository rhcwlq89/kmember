package com.example.kmember.configuration

import com.example.kmember.MemberToken
import io.jsonwebtoken.*
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.Key
import java.util.*

@Component
class JwtProvider {
    private val SECRET_KEY: Key = Keys
        .hmacShaKeyFor("L7A36218I01FC28BD2IAB00BC6DHA047".toByteArray(StandardCharsets.UTF_8))
    private val ACCESS_TOKEN_VALID_TIME = 1 * 60 * 1000L // 30분

    private val REFRESH_TOKEN_VALID_TIME = 24 * 60 * 60 * 1000L // 24시간


    fun generateTokens(authentication: Authentication): MemberToken? {
        val claims: Claims = Jwts.claims().setSubject(authentication.name)
        val now = System.currentTimeMillis()
        val accessTokenExpiredAt = Date(now + ACCESS_TOKEN_VALID_TIME)
        val refreshTokenExpiredAt = Date(now + REFRESH_TOKEN_VALID_TIME)
        return MemberToken(
            generateToken(claims, accessTokenExpiredAt),
            generateToken(claims, refreshTokenExpiredAt)
        )
    }

    private fun generateToken(claims: Claims, expiredAt: Date): String? {
        val header: Map<String, Any> = Jwts.header().setType("JWT")
        claims["roles"] = "ROLE_USER"
        claims["expiredAt"] = expiredAt
        return Jwts.builder()
            .setHeader(header)
            .setExpiration(expiredAt)
            .setClaims(claims)
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getAuthentication(token: String?): Authentication? {
        val parser: JwtParser = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()

        return try {
            val claimsJws: Jws<Claims> = parser.parseClaimsJws(token)
            val claims: Claims = claimsJws.body
            if (claims["roles"] == null) {
                throw RuntimeException("권한이 없는 토큰입니다.")
            }
            val auth: List<SimpleGrantedAuthority> = claims["roles"].toString().split(",")
                .map { role: String? -> SimpleGrantedAuthority(role) }.toList()
            val principal: UserDetails = User(claims.subject, "", auth)
            UsernamePasswordAuthenticationToken(principal, "", auth)
        } catch (e: Exception) {
            null
        }
    }
}