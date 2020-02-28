package com.jaba.code.urlshortening.service;

import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class URLEncodeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(URLEncodeService.class);
  private static final int MIN_HASH_LENGTH = 6;
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
  public static final String SALT = "this is my salt";

  private final GenerateId generateId;

  public URLEncodeService(GenerateId generateId) {
    this.generateId = generateId;
  }

  public String encodeURL(String url) {
    Hashids hashids = new Hashids(SALT, MIN_HASH_LENGTH, ALPHABET);
    long id = generateId.nextID();
    LOGGER.info("Generating HASHID for the id {}", id);
    return hashids.encode(id);
  }
}
