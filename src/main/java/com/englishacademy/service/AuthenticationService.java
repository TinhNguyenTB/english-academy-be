package com.englishacademy.service;

import com.englishacademy.dto.request.AuthenticationRequest;
import com.englishacademy.dto.request.IntrospectRequest;
import com.englishacademy.dto.request.LogoutRequest;
import com.englishacademy.dto.request.RefreshTokenRequest;
import com.englishacademy.dto.response.AuthenticationResponse;
import com.englishacademy.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
}
