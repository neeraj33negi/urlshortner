package com.urlshortner.mappers;

import com.urlshortner.entities.ShortUrl;
import com.urlshortner.models.requests.CreateUrlRequest;
import com.urlshortner.models.responses.CreateUrlResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShortUrlMapper {

    ShortUrlMapper INSTANCE = Mappers.getMapper(ShortUrlMapper.class);

    CreateUrlResponse urlToResponse(final ShortUrl shortUrl);

    ShortUrl requestToShortUrl(final CreateUrlRequest createUrlRequest);
}
