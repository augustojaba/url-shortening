package com.jaba.code.urlshortening.model;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShortenRepository extends CrudRepository<Shorten, UUID> {}
