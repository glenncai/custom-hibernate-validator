package com.glenncai.hibernate.model.vo;

/**
 * Register VO
 *
 * @author Glenn Cai
 * @version 1.0 30/10/2023
 */
public class RegisterVO {
  private String username;

  private String password;

  private String email;

  private Integer age;
  
  private Boolean agree;

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Boolean getAgree() {
    return agree;
  }

  public void setAgree(Boolean agree) {
    this.agree = agree;
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
}
