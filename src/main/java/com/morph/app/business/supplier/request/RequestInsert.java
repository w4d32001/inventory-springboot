package com.morph.app.business.supplier.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestInsert {
  @NotBlank(message = "El campo \"name\" es requerido")
  @Size(max = 50, message = "El campo \"name\" no debe tener mas de 50 caracteres")
  private String name;
  @NotBlank(message = "El campo \"name\" es requerido")
  @Size(max = 100, message = "El campo \"name\" no debe tener mas de 100 caracteres")
  private String contact;
  @NotBlank(message = "El campo \"name\" es requerido")
  @Size(max = 15, message = "El campo \"name\" no debe tener mas de 15 caracteres")
  private String phone;
  @NotBlank(message = "El campo \"name\" es requerido")
  @Size(max = 100, message = "El campo \"name\" no debe tener mas de 100 caracteres")
  private String email;
  @Size(max = 255, message = "El campo \"name\" no debe tener mas de 255 caracteres")
  private String address;
}
