package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User {

    @PrimaryKey
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String authenticationToken;
}
