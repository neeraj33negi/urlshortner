package data.repositories;

import models.ShortUrl;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ShortUrlRepository extends CassandraRepository<ShortUrl, String> {

    ShortUrl findByShortUrl(final String shortUrl);
}
