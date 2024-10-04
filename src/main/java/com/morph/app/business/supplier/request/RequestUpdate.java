package com.morph.app.business.supplier.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdate {
  @NotBlank(message = "El campo \"supplierId\" es requerido")
  @Size(max = 32, message = "El campo \"supplierId\" no debe tener mas de 32 caracteres")
  private String supplierId;
  @NotBlank(message = "El campo \"name\" es requerido")
  @Size(max = 50, message = "El campo \"name\" no debe tener mas de 50 caracteres")
  private String name;
  @NotBlank(message = "El campo \"name\" es requerido")
  @Size(max = 100, message = "El campo \"name\" no debe tener mas de 100 caracteres")
  private String contact;
  @NotBlank(message = "El campo \"name\" es requerido")
  @Size(max = 15, message = "El campo \"name\" no debe tener mas de 15 caracteres")
  private String phone;
  @Size(max = 255, message = "El campo \"name\" no debe tener mas de 255 caracteres")
  private String address;
}
