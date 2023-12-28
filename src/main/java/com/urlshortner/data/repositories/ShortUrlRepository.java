package com.urlshortner.data.repositories;

import com.urlshortner.entities.ShortUrl;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends CassandraRepository<ShortUrl, String> {

    ShortUrl findByShortUrl(final String shortUrl);
}
