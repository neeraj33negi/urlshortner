package com.urlshortner.services.impl;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class Encoder {
    private MessageDigest encoder;

    public String sevenDigitEncoded(final String string) {
        byte[] digest = encoder.digest(string.getBytes(StandardCharsets.UTF_8));
        BigInteger no = new BigInteger(1, digest);
        String str = no.toString(16);
        return str.substring(0,7);
    }

    @PostConstruct
    void initiateEncoder() {
        try {
            encoder = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
