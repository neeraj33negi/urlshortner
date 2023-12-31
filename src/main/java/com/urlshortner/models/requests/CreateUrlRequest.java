package com.urlshortner.models.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.urlshortner.entities.User;
import com.urlshortner.utils.UserUtils;
import jakarta.annotation.PostConstruct;
import lombok.*;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateUrlRequest {

    public static final Period DEFAULT_PERIOD = Period.ofYears(10);

    @NonNull
    private String longUrl;

    private String shortUrl;
    private Long expiresAt = defaultTime();
    private Long createdAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    private UUID userId;

    private static Long defaultTime() {
        LocalDateTime time = LocalDateTime.now();
        return time.plus(DEFAULT_PERIOD).toEpochSecond(ZoneOffset.UTC);
    }

    @PostConstruct
    private void assignUser() {
        User user = UserUtils.currentUser();
        if (Objects.nonNull(user)) {
            this.userId = user.getId();
        }
    }
}
