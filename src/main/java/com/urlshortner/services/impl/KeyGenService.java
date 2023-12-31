package com.urlshortner.services.impl;

import com.urlshortner.services.IKeyGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class KeyGenService implements IKeyGenService {

    @Autowired
    private Encoder encoder;

    @Override
    // handle collisions later
    public String generateShortUrl(final String longUrl) {
        return encoder.sevenDigitEncoded(longUrl);
    }
}
