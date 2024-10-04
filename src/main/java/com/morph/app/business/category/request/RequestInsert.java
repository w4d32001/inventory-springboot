package com.morph.app.business.category.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInsert {
  @NotBlank(message = "El campo \"name\" es requerido.")
  @Length(max = 50, message = "El campo \"name\" no debe tener más de 50 caracteres.")
  private String name;
  @Length(max = 255, message = "El campo \"description\" no debe tener más de 255 caracteres.")
  private String description;
}
