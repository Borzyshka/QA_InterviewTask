package com.borzyshka.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class JsonParser {

  private final ObjectMapper objectMapper;

  public <T> List<T> parseFileAsList(Class<T> targetType, String resourcePath) {
    try {
    File file = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource(resourcePath)).toURI());
      return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, targetType));
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}
