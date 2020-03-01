package com.jaba.code.urlshortening.service;

import com.jaba.code.urlshortening.model.Shorten;
import com.jaba.code.urlshortening.model.ShortenRepository;
import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class URLEncodeServiceImpl implements URLEncodeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(URLEncodeServiceImpl.class);
  private static final int MIN_HASH_LENGTH = 6;
  private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
  public static final String SALT = "this is my salt";

  private final GenerateId generateId;
  private final ShortenRepository shortenRepository;

  public URLEncodeServiceImpl(GenerateId generateId, ShortenRepository shortenRepository) {
    this.generateId = generateId;
    this.shortenRepository = shortenRepository;
  }

  @Override
  public Shorten encodeURL(String url) {
    Hashids hashids = new Hashids(SALT, MIN_HASH_LENGTH, ALPHABET);
    long id = generateId.nextID();
    LOGGER.info("Generating HASHID for the id {}", id);
    String token = hashids.encode(id);
    Shorten shorten =
        new Shorten(token, url, LocalDateTime.now(), LocalDateTime.now().plusDays(100));
    shortenRepository.save(shorten);

    return shorten;
  }
}
