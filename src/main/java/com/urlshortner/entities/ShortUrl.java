package com.urlshortner.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("short_urls")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ShortUrl {

    @PrimaryKey("short_url")
    private String shortUrl;

    @Column("long_url")
    private String longUrl;

    @Column("expires_at")
    private Long expiresAt;

    @Column("created_at")
    private Long createdAt;

    private Long clicks;

    @Column("user_id")
    private UUID userId;
}
