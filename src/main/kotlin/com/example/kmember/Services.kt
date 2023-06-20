package com.example.kmember

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignupService(
    val memberRepository: MemberRepository,
    val memberRoleRepository: MemberRoleRepository,
    val roleRepository: RoleRepository,
    val passwordEncoder: PasswordEncoder,
    val textEncryptor: TextEncryptor,
    val authenticationManagerBuilder: AuthenticationManagerBuilder,
) {

    fun signup(signUpRequest: SignUpRequest): Long? {
        val email = textEncryptor.encrypt(signUpRequest.email)
        val password = passwordEncoder.encode(signUpRequest.password)

        val member = Member(
            memberId = signUpRequest.memberId,
            name = signUpRequest.name,
            password = password,
            email = email)
        memberRepository.save(member)
        return member.memberUid
    }

}

@Service
class LoginService(val memberRepository: MemberRepository, val memberRoleRepository: MemberRoleRepository,
                    val roleRepository: RoleRepository) {
    fun login() {
        println("Login")
    }
}

@Service
class CustomUserDetailService(val memberRepository: MemberRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val grantedAuthority: GrantedAuthority = SimpleGrantedAuthority("ROLE_USER")
        val arrayList = ArrayList<GrantedAuthority>()
        arrayList.add(grantedAuthority)
        val member = memberRepository.findByMemberId(username)?.let{
            User(it.memberId, it.password, arrayList)
        }

        return member ?: throw Exception("User not found")
    }
}