package com.jaba.code.urlshortening.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GenerateId {
  private static final Logger LOGGER = LoggerFactory.getLogger(GenerateId.class);
  private static long id = 0L;

  public long nextID() {
    long newId = ++id;
    LOGGER.info("New Id Generator instantiated: {}", newId);
    return newId;
  }
}
