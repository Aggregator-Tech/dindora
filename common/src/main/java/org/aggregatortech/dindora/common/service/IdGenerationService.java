package org.aggregatortech.dindora.common.service;

import org.jvnet.hk2.annotations.Service;

import java.util.UUID;

@Service
public class IdGenerationService {
  public String generate() {
    UUID uuid = UUID.randomUUID();
    return uuid.toString();
  }
}
