package com.morph.app.business.category;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.morph.app.business.category.request.RequestInsert;
import com.morph.app.business.category.request.RequestUpdate;
import com.morph.app.business.category.response.ResponseDelete;
import com.morph.app.business.category.response.ResponseGetAll;
import com.morph.app.business.category.response.ResponseInsert;
import com.morph.app.business.category.response.ResponseUpdate;
import com.morph.app.dto.DtoCategory;
import com.morph.app.helpers.category.ConvertDtoCategory;
import com.morph.app.helpers.category.ResponseCategoryHelper;
import com.morph.app.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;

  @GetMapping
  public ResponseEntity<ResponseGetAll> index() {
    ResponseGetAll responseGetAll = new ResponseGetAll();
    List<DtoCategory> listDtoCategory = categoryService.getAll();

    responseGetAll.dto.listCategory = listDtoCategory.stream()
        .map(dtoCategory -> {
          Map<String, Object> map = new HashMap<>();
          map.put("categoryId", dtoCategory.getCategoryId());
          map.put("name", dtoCategory.getName());
          map.put("description", dtoCategory.getDescription());
          return map;
        }).collect(Collectors.toList());

    responseGetAll.success();

    return new ResponseEntity<>(responseGetAll, HttpStatus.OK);
  }

  @PostMapping(consumes = { "multipart/form-data" })
  public ResponseEntity<ResponseInsert> store(@Valid @ModelAttribute RequestInsert soInsert,
      BindingResult bindingResult) {
    ResponseInsert responseInsert = new ResponseInsert();
    if (bindingResult.hasErrors()) {
      return ResponseCategoryHelper.handleValidationErrors(bindingResult, responseInsert);
    }
    try {
      DtoCategory dtoCategory = ConvertDtoCategory.convertDtoCategory(soInsert);
      categoryService.insert(dtoCategory);
      responseInsert.success();
      responseInsert.addResponseMesssage("Operación realizada exitosamente");
      return new ResponseEntity<>(responseInsert, HttpStatus.CREATED);
    } catch (Exception e) {
      responseInsert.exception();
      responseInsert.addResponseMesssage("Ocurrió un error inesperado: " + e.getMessage());
      return new ResponseEntity<>(responseInsert, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ResponseDelete> delete(@PathVariable String id) {
    ResponseDelete responseDelete = new ResponseDelete();
    try {
      boolean isDeleted = categoryService.delete(id);
      if (isDeleted) {
        responseDelete.success();
        responseDelete.addResponseMesssage("Operacion realizada correctamente");
        return new ResponseEntity<>(responseDelete, HttpStatus.OK);
      } else {
        responseDelete.error();
        responseDelete.addResponseMesssage("No se encontró el registro a eliminar");
        return new ResponseEntity<>(responseDelete, HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      responseDelete.exception();
      responseDelete.addResponseMesssage("Ocurrio un error inesperado: " + e.getMessage());
      return new ResponseEntity<>(responseDelete, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(path = "update", consumes = { "multipart/form-data" })
  public ResponseEntity<ResponseUpdate> update(@Valid @ModelAttribute RequestUpdate soUpdate) {
    ResponseUpdate responseUpdate = new ResponseUpdate();
    DtoCategory dtoCategory = new DtoCategory();
    dtoCategory.setCategoryId(soUpdate.getCategoryId());
    dtoCategory.setName(soUpdate.getName());
    dtoCategory.setDescription(soUpdate.getDescription());
    try {
      boolean isUpdated = categoryService.update(dtoCategory);
      if (isUpdated) {
        responseUpdate.success();
        responseUpdate.addResponseMesssage("Operacion realizada correctamente.");
        return ResponseEntity.ok(responseUpdate);
      } else {
        responseUpdate.error();
        responseUpdate.addResponseMesssage("No se encontró la categoría o no se pudo actualizar");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseUpdate);
      }

    } catch (Exception e) {
      responseUpdate.exception();
      responseUpdate.addResponseMesssage("Ocurrió un error inesperado, estamos trabajando para solucionarlo.");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseUpdate);
    }

  }

}
