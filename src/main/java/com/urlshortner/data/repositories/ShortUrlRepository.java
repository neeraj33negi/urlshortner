package com.urlshortner.data.repositories;

import com.urlshortner.entities.ShortUrl;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortUrlRepository extends CassandraRepository<ShortUrl, String> {

    Optional<ShortUrl> findByShortUrl(final String shortUrl);
}
