package com.urlshortner.data.reposervices;

import com.urlshortner.data.repositories.ShortUrlRepository;
import com.urlshortner.entities.ShortUrl;
import com.urlshortner.entities.User;
import com.urlshortner.mappers.ShortUrlMapper;
import com.urlshortner.models.requests.CreateUrlRequest;
import com.urlshortner.services.IKeyGenService;
import com.urlshortner.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
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

    public ShortUrl createShortUrl(final CreateUrlRequest createUrlRequest) throws URISyntaxException {
        URI uri = new URI(createUrlRequest.getLongUrl());
        if (Objects.isNull(uri.getScheme())) {
            uri = UriComponentsBuilder.fromUri(uri).scheme("https").build().toUri();
        }
        createUrlRequest.setLongUrl(uri.toString());
        createUrlRequest.setShortUrl(keyGenService.generateShortUrl(createUrlRequest.getLongUrl()));
        createUrlRequest.setCreatedAt(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        assignUser(createUrlRequest);
        ShortUrl shortUrl = ShortUrlMapper.INSTANCE.requestToShortUrl(createUrlRequest);
        repository.save(shortUrl);
        return shortUrl;
    }

    private void assignUser(final CreateUrlRequest request) {
        User user = UserUtils.currentUser();
        if (Objects.nonNull(user)) {
            request.setUserId(user.getId());
        }
    }
}
