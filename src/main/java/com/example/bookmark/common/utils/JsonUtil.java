package com.example.bookmark.common.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

   public static<T> T convertJsonNodeToObject(JsonNode jsonNode, Class<T> type) throws Exception {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      return objectMapper.convertValue(jsonNode, type);
    } catch (Exception e) {
      throw new Exception("Error while parsing jsonNode");
    }
  }

  public static<T> JsonNode convertObjectToJsonNode(T object) throws Exception {
     try {
       ObjectMapper objectMapper = new ObjectMapper();
       if (object.getClass() == String.class) {
         return objectMapper.readTree(object.toString());
       }
       return objectMapper.convertValue(object, JsonNode.class);
     } catch (Exception e) {
       throw new Exception("Error while converting object to JsonNode");
     }
  }
}
