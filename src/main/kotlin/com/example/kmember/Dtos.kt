package com.example.kmember

import org.springframework.security.core.userdetails.User


data class SignUpRequest(
    val memberId: String,
    val name: String,
    val password: String,
    val email: String
)

data class LoginRequest(
    val memberId: String,
    val password: String
)

data class MemberToken(
    val accessToken: String? = null,
    val refreshToken: String? = null
)

data class MemberInfo(
    val memberId: User
)