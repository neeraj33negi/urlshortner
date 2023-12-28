package data.repositories;

import models.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface UserRepository extends CassandraRepository<User, UUID> {

    User findByUuid(final UUID uuid);

    @AllowFiltering
    User findByNameAndPassword(final String name, final String password); //Password Salt would be expected
}
