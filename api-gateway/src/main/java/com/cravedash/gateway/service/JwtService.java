package com.cravedash.gateway.service;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service
public class JwtService {

    @Value("${app.jwt.expiration}")
    private long expiration;

    private static final String SECRET =
            "CraveDashSuperSecretKeyForJWT2026Project123456789";

    public String generateToken(String username) {

        try {

            Instant now = Instant.now();

            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issueTime(Date.from(now))
                    .expirationTime(
                            Date.from(now.plusMillis(expiration))
                    )
                    .claim("role", "USER")
                    .build();

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claims
            );

            signedJWT.sign(
                    new MACSigner(SECRET.getBytes())
            );

            return signedJWT.serialize();

        } catch (JOSEException e) {

            throw new RuntimeException(
                    "Error generating JWT token",
                    e
            );
        }
    }
}