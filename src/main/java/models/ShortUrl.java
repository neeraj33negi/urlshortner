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
public class ShortUrl {

    @PrimaryKey
    private String shortUrl;
    private String longUrl;
    private Long expiresAt;
    private Long createdAt;
    private Long clicks;
    private UUID userId;
}
