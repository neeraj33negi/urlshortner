package com.urlshortner.controllers;

import com.urlshortner.entities.ShortUrl;
import com.urlshortner.services.IShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/")
public class RedirectController {

    @Autowired
    private IShortUrlService urlService;

    @GetMapping("/{path:[^\\.]+}/**")
    public ResponseEntity<Object> forward(HttpServletResponse response) throws IOException, URISyntaxException {
//        ShortUrl url = urlService.findByShortUrl("a");
        URI uri = new URI("http://www.google.com");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
    }
}
