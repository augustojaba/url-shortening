package com.jaba.code.urlshortening.service;

import com.jaba.code.urlshortening.model.Shorten;
import com.jaba.code.urlshortening.model.ShortenRepository;
import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class URLEncodeServiceImpl implements URLEncodeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(URLEncodeServiceImpl.class);
  private static final int MIN_HASH_LENGTH = 6;
  private static final String ALPHABET =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  // TODO: Use a salt based on the request
  public static final String SALT = "this is my salt";

  private final GenerateId generateId;
  private final ShortenRepository shortenRepository;
  private final Hashids hashids;

  public URLEncodeServiceImpl(GenerateId generateId, ShortenRepository shortenRepository) {
    this.generateId = generateId;
    this.shortenRepository = shortenRepository;
    hashids = new Hashids(SALT, MIN_HASH_LENGTH, ALPHABET);
  }

  @Override
  public Shorten encodeURL(String url) {

    long id = generateId.nextID();
    LOGGER.info("Generating HASHID for the id {}", id);

    Shorten shorten = new Shorten(getHashIdToken(id), url);
    shortenRepository.save(shorten);

    return shorten;
  }

  private synchronized String getHashIdToken(long id) {
    return hashids.encode(id);
  }

  @Override
  public Shorten decodeShortUrl(String token) {
    Shorten byToken = shortenRepository.findByToken(token);
    if (null == byToken) {
      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND, "Token was not found or it is expired");
    }
    return byToken;
  }

  @Override
  public Shorten findLongUrl(String longUrl) {
    return shortenRepository.findByLongUrl(longUrl);
  }
}
