package com.urlshortner.data.repositories;

import com.urlshortner.entities.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {

    User findByEmailAndPassword(final String email, final String password); //Password Salt would be expected
}
