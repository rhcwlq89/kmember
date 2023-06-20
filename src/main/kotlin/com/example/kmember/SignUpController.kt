package com.example.kmember

import com.example.kmember.SignUpRequest
import com.example.kmember.SignupService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SignUpController(val signupService: SignupService) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody singUpRequest: SignUpRequest): Long? {
        return signupService.signup(singUpRequest)
    }
}