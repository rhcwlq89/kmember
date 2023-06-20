package com.example.kmember


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