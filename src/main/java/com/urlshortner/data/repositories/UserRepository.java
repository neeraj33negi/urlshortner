package com.urlshortner.data.repositories;

import com.urlshortner.entities.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {

    @AllowFiltering
    Optional<User> findById(final UUID uuid);
    Optional<User> findByEmail(final String email);

    @AllowFiltering
    Optional<User> findByAuthenticationToken(final String token);
}
