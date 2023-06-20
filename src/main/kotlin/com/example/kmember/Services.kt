package com.example.kmember

import org.springframework.stereotype.Service

@Service
class SignupService(val memberRepository: MemberRepository, val memberRoleRepository: MemberRoleRepository,
                    val roleRepository: RoleRepository) {

    fun signup() {
        Member
    }

}

@Service
class LoginService(val memberRepository: MemberRepository, val memberRoleRepository: MemberRoleRepository,
                    val roleRepository: RoleRepository) {
    fun login() {
        println("Login")
    }
}