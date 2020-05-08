package com.jaba.code.urlshortening.controller;

import com.jaba.code.urlshortening.model.Shorten;
import com.jaba.code.urlshortening.service.URLEncodeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class URLController {

  private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
  private final URLEncodeService urlEncodeService;

  public URLController(URLEncodeService urlEncodeService) {
    this.urlEncodeService = urlEncodeService;
  }

  @GetMapping
  public void redirectShortUrl(
      @RequestParam(name = "token") String token, HttpServletResponse response) throws IOException {
    LOGGER.info("Redirect short url");
    Shorten decodedUrl = urlEncodeService.decodeShortUrl(token);
    LOGGER.info("Url to redirect {}", decodedUrl.getLongUrl());
    response.sendRedirect(decodedUrl.getLongUrl());
  }

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Shorten> generateShortUrl(@RequestParam(name = "url") String url) {
    LOGGER.info("Start generate short URL for: {}", url);
    Shorten shortenAlreadyExists = urlEncodeService.findLongUrl(url);
    if (shortenAlreadyExists != null && StringUtils.isNotBlank(shortenAlreadyExists.getLongUrl())) {
      return new ResponseEntity<>(shortenAlreadyExists, HttpStatus.OK);
    }
    return new ResponseEntity<>(urlEncodeService.encodeURL(url), HttpStatus.CREATED);
  }
}
