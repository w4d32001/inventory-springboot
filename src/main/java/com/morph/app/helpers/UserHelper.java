package com.morph.app.helpers;

import org.apache.el.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.morph.app.business.person.request.RequestInsert;
import com.morph.app.business.person.response.ResponseInsert;
import com.morph.app.dto.DtoUser;

public class UserHelper {
  public static ResponseEntity<ResponseInsert> handleValidationErrors(BindingResult bindingResult, ResponseInsert responseInsert){
    bindingResult.getAllErrors().forEach(error -> {
      responseInsert.addResponseMesssage(error.getDefaultMessage());
    });
    return new ResponseEntity<>(responseInsert, HttpStatus.BAD_REQUEST);
  }
  public static DtoUser convertToDtoUser(RequestInsert soInsert) throws ParseException{
    DtoUser dtoUser = new DtoUser();
    dtoUser.setUserName(soInsert.getUserName());
    dtoUser.setPassword(soInsert.getPassword());
    dtoUser.setEmail(soInsert.getEmail());
    return dtoUser;
  }
}
