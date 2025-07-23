package com.englishacademy.controller;


import com.englishacademy.dto.request.AuthenticationRequest;
import com.englishacademy.dto.request.IntrospectRequest;
import com.englishacademy.dto.request.LogoutRequest;
import com.englishacademy.dto.request.RefreshTokenRequest;
import com.englishacademy.dto.response.AuthenticationResponse;
import com.englishacademy.dto.response.IntrospectResponse;
import com.englishacademy.dto.response.ResponseData;
import com.englishacademy.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseData<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        AuthenticationResponse result = authenticationService.authenticate(request);

        return ResponseData.<AuthenticationResponse>builder()
                .data(result)
                .message("Login")
                .code(HttpStatus.OK.value())
                .build();
    }

    @PostMapping("/logout")
    public ResponseData<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ResponseData.<Void>builder()
                .message("logout")
                .build();
    }

    @PostMapping("/introspect")
    public ResponseData<IntrospectResponse> introspect (@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        IntrospectResponse result =authenticationService.introspect(request);
        return ResponseData.<IntrospectResponse>builder()
                .code(HttpStatus.OK.value())
                .message("introspect")
                .data(result)
                .build();
    }

    @PostMapping("/refresh")
    public ResponseData<AuthenticationResponse> refresh(@RequestBody RefreshTokenRequest request) throws ParseException, JOSEException {
        AuthenticationResponse result = authenticationService.refreshToken(request);

        return ResponseData.<AuthenticationResponse>builder()
                .data(result)
                .message("Login")
                .code(HttpStatus.OK.value())
                .build();
    }

}
