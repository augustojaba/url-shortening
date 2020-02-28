package com.jaba.code.urlshortening.controller;

import com.jaba.code.urlshortening.service.URLEncodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class URLController {

  private static final Logger LOGGER = LoggerFactory.getLogger(URLController.class);
  private final URLEncodeService urlEncodeService;

  public URLController(URLEncodeService urlEncodeService) {
    this.urlEncodeService = urlEncodeService;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public String generateShortUrl(@RequestParam(name = "url") String url) {

    LOGGER.info("Start generate short URL for: {}", url);
    return urlEncodeService.encodeURL(url);
  }
}
