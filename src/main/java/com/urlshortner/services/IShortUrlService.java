package com.urlshortner.services;

import com.urlshortner.entities.ShortUrl;
import com.urlshortner.models.requests.CreateUrlRequest;
import com.urlshortner.models.responses.CreateUrlResponse;

public interface IShortUrlService {

    ShortUrl findByShortUrl(final String shortUrl);

    CreateUrlResponse createShortUrl(final CreateUrlRequest createUrlRequest);
}
