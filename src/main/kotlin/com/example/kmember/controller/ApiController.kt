package com.example.kmember.controller

import com.example.kmember.MemberInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController {

    @GetMapping("/api/hello")
    fun hello(memberInfo: MemberInfo): String {
        println(memberInfo.memberId)
        return "hello"
    }
}