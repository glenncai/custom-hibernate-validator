package com.glenncai.hibernate.controller;

import com.glenncai.hibernate.common.ResponseHandler;
import com.glenncai.hibernate.model.dto.RegisterRequest;
import com.glenncai.hibernate.model.vo.RegisterVO;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Different controller for different form
 *
 * @author Glenn Cai
 * @version 1.0 30/10/2023
 */
@RestController
@RequestMapping("/hibernate")
public class FormController {

  Logger logger = org.slf4j.LoggerFactory.getLogger(FormController.class);

  @PostMapping("/register")
  public ResponseEntity<Object> registerController(
      @Valid @RequestBody RegisterRequest registerRequest,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      bindingResult.getFieldErrors().forEach(
          fieldError -> logger.error("Register request body has error: {}",
                                     fieldError.getDefaultMessage()));
      return ResponseHandler.baseResponse("Parameter error", HttpStatus.BAD_REQUEST, null);
    }
    RegisterVO registerVO = new RegisterVO();
    registerVO.setUsername(registerRequest.getUsername());
    registerVO.setPassword(registerRequest.getPassword());
    registerVO.setEmail(registerRequest.getEmail());
    registerVO.setAge(registerRequest.getAge());
    registerVO.setAgree(registerRequest.getAgree());

    logger.info("Register request body: {}", registerRequest);
    return ResponseHandler.baseResponse("ok", HttpStatus.OK, registerVO);
  }
}
