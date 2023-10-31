package com.glenncai.hibernate.model.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenncai.hibernate.validator.Age;
import org.slf4j.Logger;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User register request DTO
 *
 * @author Glenn Cai
 * @version 1.0 30/10/2023
 */
public class RegisterRequest {

  Logger logger = org.slf4j.LoggerFactory.getLogger(RegisterRequest.class);

  @NotBlank(message = "Username is required")
  @Size(min = 4, max = 20, message = "Username length must between 4 and 20")
  private String username;

  @NotBlank(message = "Password is required")
  private String password;

  @NotBlank(message = "Email is required")
  @Email(message = "Email is invalid")
  private String email;

  @Age(lower = 16, upper = 65, message = "Age must between 16 and 65")
  private Integer age;

  @NotNull(message = "Must agree the terms")
  @AssertTrue(message = "Must agree the terms")
  private Boolean agree;

  public Boolean getAgree() {
    return agree;
  }

  public void setAgree(Boolean agree) {
    this.agree = agree;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      logger.error("JsonProcessingException: {}", e.getMessage());
    }
    return "";
  }
}
