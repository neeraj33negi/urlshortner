package com.urlshortner.controllers;

import com.urlshortner.models.requests.CreateUrlRequest;
import com.urlshortner.models.responses.CreateUrlResponse;
import com.urlshortner.services.IShortUrlService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/api/v1/urls")
public class UrlsController {

    @Autowired
    private IShortUrlService service;

    @PostMapping(value = "/generate")
    public ResponseEntity<CreateUrlResponse> generateShortUrl(
            @RequestBody final CreateUrlRequest request) {
        try {
            return new ResponseEntity<>(service.createShortUrl(request), HttpStatus.OK);
        } catch (URISyntaxException ex) {
            return new ResponseEntity<>(CreateUrlResponse.builder().errors("Invalid Url").build(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(value = "/")
    public String test() {
        return "a";
    }
}
