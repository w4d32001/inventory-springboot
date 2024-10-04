package com.morph.app.business.person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morph.app.business.person.request.RequestInsert;
import com.morph.app.business.person.request.RequestUpdate;
import com.morph.app.business.person.response.ResponseDelete;
import com.morph.app.business.person.response.ResponseGetAll;
import com.morph.app.business.person.response.ResponseInsert;
import com.morph.app.business.person.response.ResponseUpdate;
import com.morph.app.dto.DtoUser;
import com.morph.app.helpers.UserHelper;
import com.morph.app.service.PersonService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("person")
public class PersonController {
  @Autowired
  private PersonService personService;

  @GetMapping
  public ResponseEntity<ResponseGetAll> index() {
    ResponseGetAll responseGetAll = new ResponseGetAll();

    List<DtoUser> listDtoPerson = personService.getAll();

    responseGetAll.dto.listPerson = listDtoPerson.stream()
        .map(dtoPerson -> {
          Map<String, Object> map = new HashMap<>();
          map.put("userId", dtoPerson.getUserId());
          map.put("firstName", dtoPerson.getUserName());
          map.put("password", dtoPerson.getPassword());
          map.put("email", dtoPerson.getEmail());
          return map;
        })
        .collect(Collectors.toList());

    responseGetAll.success();

    return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
  }

  @PostMapping(consumes = { "multipart/form-data" })
  public ResponseEntity<ResponseInsert> store(@Valid @ModelAttribute RequestInsert soInsert,
      BindingResult bindingResult) {
    ResponseInsert responseInsert = new ResponseInsert();

    if (bindingResult.hasErrors()) {
      return UserHelper.handleValidationErrors(bindingResult, responseInsert);
    }

    try {
      DtoUser dtoUser = UserHelper.convertToDtoUser(soInsert);
      personService.insert(dtoUser);
      responseInsert.success();
      responseInsert.addResponseMesssage("Operacion realizada correctamente");
      return new ResponseEntity<>(responseInsert, HttpStatus.CREATED);
    } catch (ParseException e) {
      responseInsert.exception();
      responseInsert.addResponseMesssage("Formato de fecha no valido: " + e.getMessage());
      return new ResponseEntity<>(responseInsert, HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
      responseInsert.exception();
      responseInsert.addResponseMesssage("Ocurrio un error inesperado: " + e.getMessage());
      return new ResponseEntity<>(responseInsert, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDelete> delete(@PathVariable String id) {
    ResponseDelete responseDelete = new ResponseDelete();

    try {
      boolean isDeleted = personService.delete(id);
      if (isDeleted) {
        responseDelete.success();
        responseDelete.addResponseMesssage("Operacion realizada correctamente.");
        return new ResponseEntity<>(responseDelete, HttpStatus.OK);
      } else {
        responseDelete.error();
        responseDelete.addResponseMesssage("No se encontro el registro a eliminar");
        return new ResponseEntity<>(responseDelete, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      responseDelete.exception();
      responseDelete.addResponseMesssage("Ocurrio un error inesperado: " + e.getMessage());
      return new ResponseEntity<>(responseDelete, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(path = "update", consumes = { "multipart/form-data" })
  public ResponseEntity<ResponseUpdate> update(@ModelAttribute RequestUpdate soUpdate) {
    ResponseUpdate responseUpdate = new ResponseUpdate();

    DtoUser dtoUser = new DtoUser();
    dtoUser.setUserId(soUpdate.getUserId());
    dtoUser.setUserName(soUpdate.getUserName());
    dtoUser.setEmail(soUpdate.getEmail());

    try {
      personService.update(dtoUser);
      responseUpdate.success();
      responseUpdate.addResponseMesssage("Operacion realizada exitosamente");
      return ResponseEntity.ok(responseUpdate);
    } catch (Exception e) {
      responseUpdate.exception();
      responseUpdate.addResponseMesssage("Ocurrió un error inesperado, estamos trabajando para solucionarlo.");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseUpdate);
    }
  }

}
