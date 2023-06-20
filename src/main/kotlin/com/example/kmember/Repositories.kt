package com.example.kmember

import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun findByMemberId(memberId: String?): Member?

}

interface MemberRoleRepository: JpaRepository<MemberRole, Long> {

}

interface RoleRepository: JpaRepository<Role, Long> {

}