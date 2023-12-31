package com.urlshortner.controllers;

import com.urlshortner.models.requests.CreateUrlRequest;
import com.urlshortner.models.responses.CreateUrlResponse;
import com.urlshortner.services.IShortUrlService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/urls")
public class UrlsController {

    @Autowired
    private IShortUrlService service;

    @PostMapping(value = "/generate")
    public CreateUrlResponse generateShortUrl(@RequestBody final CreateUrlRequest request) {
        return service.createShortUrl(request);
    }

    @GetMapping(value = "/")
    public String test() {
        return "a";
    }
}
