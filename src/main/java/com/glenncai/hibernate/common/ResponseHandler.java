package com.glenncai.hibernate.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom base response
 *
 * @author Glenn Cai
 * @version 1.0 31/10/2023
 */
public class ResponseHandler {

  private ResponseHandler() {
  }

  public static ResponseEntity<Object> baseResponse(String message, HttpStatus status,
                                                    Object responseObj) {
    Map<String, Object> map = new HashMap<>();
    map.put("message", message);
    map.put("code", status.value());
    map.put("data", responseObj);
    return new ResponseEntity<>(map, status);
  }
}
