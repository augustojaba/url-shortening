package com.jaba.code.urlshortening.service;

import com.jaba.code.urlshortening.model.Shorten;

public interface URLEncodeService {
  Shorten encodeURL(String url);
}
