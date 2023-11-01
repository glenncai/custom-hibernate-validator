package com.glenncai.hibernate.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glenncai.hibernate.model.dto.RegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * Form controller test
 *
 * @author Glenn Cai
 * @version 1.0 1/11/2023
 */
@TestPropertySource(value = "classpath:application.properties")
@AutoConfigureMockMvc
@SpringBootTest
class FormControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Test register controller success")
  void testRegisterControllerSuccess() throws Exception {
    // Post with @RequestBody need to use json format
    String formData =
        "{\"username\":\"john\",\"password\":\"test123456!\",\"age\":18," +
            "\"email\":\"john_lee@gmail.com\",\"agree\":true}";

    // Make sure to use `content` in order to post with @RequestBody
    MvcResult mvcResult = mockMvc.perform(
                                     post("/hibernate/register").contentType(MediaType.APPLICATION_JSON).content(formData))
                                 .andExpect(status().isOk())
                                 .andReturn();

    String response = mvcResult.getResponse().getContentAsString();

    ObjectMapper objectMapper = new ObjectMapper();
    // Use JsonNode to deserialize only the portion of the JSON
    JsonNode jsonNode = objectMapper.readTree(response);
    JsonNode data = jsonNode.get("data");
    RegisterRequest registerRequest = objectMapper.treeToValue(data, RegisterRequest.class);

    assertEquals("john", registerRequest.getUsername());
    assertEquals("test123456!", registerRequest.getPassword());
    assertEquals("john_lee@gmail.com", registerRequest.getEmail());
    assertEquals(true, registerRequest.getAgree());
    assertEquals(18, registerRequest.getAge());
  }

  @Test
  @DisplayName("Test register controller fail")
  void testRegisterControllerFail() throws Exception {
    // Post with @RequestBody need to use json format
    String formData =
        "{\"username\":\"john\",\"password\":\"test123456!\",\"age\":1," +
            "\"email\":\"john_lee@gmail.com\",\"agree\":true}";

    // Make sure to use `content` in order to post with @RequestBody
    MvcResult mvcResult = mockMvc.perform(
                                     post("/hibernate/register").contentType(MediaType.APPLICATION_JSON).content(formData))
                                 .andExpect(status().is4xxClientError())
                                 .andReturn();

    String response = mvcResult.getResponse().getContentAsString();

    ObjectMapper objectMapper = new ObjectMapper();
    // Use JsonNode to deserialize only the portion of the JSON
    JsonNode jsonNode = objectMapper.readTree(response);
    JsonNode message = jsonNode.get("message");
    String errorMessage = objectMapper.treeToValue(message, String.class);

    assertEquals("Parameter error", errorMessage);
  }
}