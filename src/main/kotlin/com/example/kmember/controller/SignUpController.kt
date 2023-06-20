package com.example.kmember.controller

import com.example.kmember.SignupService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController(val signupService: SignupService) {

    @PostMapping("/sign-up")
    fun signUp() {
        signupService.signup()
    }
}