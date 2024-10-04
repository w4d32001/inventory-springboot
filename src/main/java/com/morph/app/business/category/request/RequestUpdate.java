package com.morph.app.business.category.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Setter;
import lombok.Getter;
@Setter
@Getter
public class RequestUpdate {
  @NotBlank(message = "El campo \"categoryId\" no debe estar vacío.")
  @Length(max = 36, message = "El campo \"categoryId\" no debe tener más de 36 caracteres.")
  private String categoryId;
  @Length(max = 50, message = "El campo \"name\" no debe tener más de 50 caracteres.")
  private String name;
  @Length(max = 255, message = "El campo \"description\" no debe tener más de 255 caracteres.")
  private String description;
}
