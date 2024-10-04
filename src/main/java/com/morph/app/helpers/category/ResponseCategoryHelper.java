package com.morph.app.helpers.category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.morph.app.business.category.response.ResponseInsert;

public class ResponseCategoryHelper {
  public static ResponseEntity<ResponseInsert> handleValidationErrors(BindingResult bindingResult,
      ResponseInsert responseInsert) {
    bindingResult.getAllErrors().forEach(error -> {
      responseInsert.addResponseMesssage(error.getDefaultMessage());
    });

    return new ResponseEntity<>(responseInsert, HttpStatus.BAD_REQUEST);
  }
}
