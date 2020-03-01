package com.jaba.code.urlshortening.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Shorten {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "BINARY(16)")
  private UUID id;

  private String token;

  @Column(name = "long_url")
  private String longUrl;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "expiry_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime expiryAt;

  protected Shorten() {}

  public Shorten(String token, String longUrl, LocalDateTime createdAt, LocalDateTime expiryAt) {
    this.token = token;
    this.longUrl = longUrl;
    this.createdAt = createdAt;
    this.expiryAt = expiryAt;
  }

  public UUID getId() {
    return id;
  }

  public String getToken() {
    return token;
  }

  public String getLongUrl() {
    return longUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getExpiryAt() {
    return expiryAt;
  }
}
