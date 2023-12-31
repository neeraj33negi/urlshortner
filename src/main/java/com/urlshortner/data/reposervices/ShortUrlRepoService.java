package com.urlshortner.data.reposervices;

import com.urlshortner.data.repositories.ShortUrlRepository;
import com.urlshortner.entities.ShortUrl;
import com.urlshortner.mappers.ShortUrlMapper;
import com.urlshortner.models.requests.CreateUrlRequest;
import com.urlshortner.services.IKeyGenService;
import com.urlshortner.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShortUrlRepoService {

    @Autowired
    private ShortUrlRepository repository;

    @Autowired
    private IKeyGenService keyGenService;

    public ShortUrl findByShortUrl(final String shortUrl) {
        Optional<ShortUrl> urlOptional = repository.findByShortUrl(shortUrl);
        if (Boolean.TRUE.equals(urlOptional.isPresent())) {
            return urlOptional.get();
        }
        return null;
    }

    public ShortUrl createShortUrl(final CreateUrlRequest createUrlRequest) {
        createUrlRequest.setShortUrl(keyGenService.generateShortUrl(createUrlRequest.getLongUrl()));
        createUrlRequest.setCreatedAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        ShortUrl shortUrl = ShortUrlMapper.INSTANCE.requestToShortUrl(createUrlRequest);
        repository.save(shortUrl);
        return shortUrl;
    }
}
