package com.example.kmember

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var memberUid: Long ?= null,
    var memberId: String,
    var name: String,
    var password: String,
    var email: String
)

@Entity
class MemberRole(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var memberRoleUid: Long ?= null,
    var RoleUid: Long,
    var memberUid: Long,
)

@Entity
class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var roleUid: Long ?= null,
    var roleName: String,
)
