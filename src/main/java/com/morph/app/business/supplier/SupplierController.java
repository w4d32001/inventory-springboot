package com.morph.app.business.supplier;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morph.app.business.supplier.response.ResponseInsert;
import com.morph.app.business.supplier.response.ResponseUpdate;
import com.morph.app.business.supplier.request.RequestInsert;
import com.morph.app.business.supplier.request.RequestUpdate;
import com.morph.app.business.supplier.response.ResponseDelete;
import com.morph.app.business.supplier.response.ResponseGetAll;
import com.morph.app.dto.DtoSupplier;
import com.morph.app.helpers.supplier.ConvertDtoSupplier;
import com.morph.app.helpers.supplier.ResponseSupplierHelper;
import com.morph.app.service.SupplierService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("supplier")
public class SupplierController {
  @Autowired
  private SupplierService supplierService;

  @GetMapping
  public ResponseEntity<ResponseGetAll> index(){
    List<DtoSupplier> listDtoSupplier = this.supplierService.getAll() ;
    ResponseGetAll responseGetAll = new ResponseGetAll();
    responseGetAll.dto.listSupplier = listDtoSupplier.stream().map(dtoSupplier -> {
      Map<String, Object> map = new HashMap<>();

      return map;
    }).collect(Collectors.toList());
    responseGetAll.success();
    return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
  }
  
  @PostMapping( consumes = { "multipart/form-data" } )
  public ResponseEntity<ResponseInsert> store(@Valid @ModelAttribute RequestInsert soInsert, BindingResult bindingResult){
    ResponseInsert responseInsert = new ResponseInsert();
    if (bindingResult.hasErrors()) {
      return ResponseSupplierHelper.handleValidationErrors(bindingResult, responseInsert);
    }
    try{
      DtoSupplier dtoSupplier = ConvertDtoSupplier.convertDtoSupplier(soInsert);
      this.supplierService.insert(dtoSupplier);
      responseInsert.success();
      responseInsert.addResponseMesssage("Operacion realizada exitosamente");
      return new ResponseEntity<>(responseInsert, HttpStatus.CREATED);
    }catch(Exception e){
      responseInsert.exception();
      responseInsert.addResponseMesssage("Ocurrio un error inesperado: " + e.getMessage());
      return new ResponseEntity<>(responseInsert, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/id")
  public ResponseEntity<ResponseDelete> delete(@PathVariable String id){
    ResponseDelete responseDelete = new ResponseDelete();
    try{
      boolean isDeleted = this.supplierService.delete(id);
      if(isDeleted){
        responseDelete.success();
        responseDelete.addResponseMesssage("Operacion realizada exitosamente");
        return new ResponseEntity<>(responseDelete, HttpStatus.OK);
      }else{
        responseDelete.error();
        responseDelete.addResponseMesssage("Ocurrio un error inesperado");
        return new ResponseEntity<>(responseDelete, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }catch(Exception e){
      responseDelete.exception();
      responseDelete.addResponseMesssage("Ocurrió un error inesperado: " + e.getMessage());
      return new ResponseEntity<>(responseDelete, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  
  @PostMapping(path = "update", consumes = { "multipart/form-data" } )
  public ResponseEntity<ResponseUpdate> update(@Valid @ModelAttribute RequestUpdate soUpdate){

    ResponseUpdate responseUpdate = new ResponseUpdate();
    DtoSupplier dtoSupplier = new DtoSupplier();
    dtoSupplier.setAddress(soUpdate.getSupplierId());
    dtoSupplier.setName(soUpdate.getName());
    dtoSupplier.setContact(soUpdate.getContact());
    dtoSupplier.setPhone(soUpdate.getPhone());
    dtoSupplier.setAddress(soUpdate.getAddress());

    try {
      boolean isUpdated = this.supplierService.update(dtoSupplier);
      if(isUpdated){
        responseUpdate.success();
        responseUpdate.addResponseMesssage("Operacion realizada exitosamente.");
        return new ResponseEntity<>(responseUpdate, HttpStatus.OK);
      }else{
        responseUpdate.error();
        responseUpdate.addResponseMesssage("No se encontró la categoría o no se pudo actualizar.");
        return new ResponseEntity<>(responseUpdate, HttpStatus.INTERNAL_SERVER_ERROR);
      }
    } catch (Exception e) {
      responseUpdate.exception();
      responseUpdate.addResponseMesssage("Ocurrió un error inesperado, estamos trabajando para solucionarlo.");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseUpdate);
    }

  }
  

}
