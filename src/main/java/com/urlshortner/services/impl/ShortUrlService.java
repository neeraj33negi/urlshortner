package com.urlshortner.services.impl;

import com.urlshortner.data.reposervices.ShortUrlRepoService;
import com.urlshortner.entities.ShortUrl;
import com.urlshortner.mappers.ShortUrlMapper;
import com.urlshortner.models.requests.CreateUrlRequest;
import com.urlshortner.models.responses.CreateUrlResponse;
import com.urlshortner.services.IShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService implements IShortUrlService {

    @Autowired
    private ShortUrlRepoService repoService;

    @Override
    public CreateUrlResponse createShortUrl(CreateUrlRequest createUrlRequest) {
        ShortUrl shortUrl = repoService.createShortUrl(createUrlRequest);
        return ShortUrlMapper.INSTANCE.urlToResponse(shortUrl);
    }

    @Override
    public ShortUrl findByShortUrl(String shortUrl) {
        return repoService.findByShortUrl(shortUrl);
    }
}
