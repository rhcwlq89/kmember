package com.example.kmember.controller

import com.example.kmember.LoginRequest
import com.example.kmember.LoginService
import com.example.kmember.MemberToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(val loginService: LoginService) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): MemberToken? {
        
        return loginService.login(loginRequest)
    }

}