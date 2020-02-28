package com.jaba.code.urlshortening.service;

import org.springframework.stereotype.Component;

@Component
public class GenerateId {

  private static long id = 0L;

  public long nextID() {
    return ++id;
  }
}
