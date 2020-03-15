package com.jaba.code.urlshortening.model;

import org.springframework.data.repository.CrudRepository;

public interface ShortenRepository extends CrudRepository<Shorten, String> {

  Shorten findByToken(String token);

  Shorten findByLongUrl(String longUrl);
}
