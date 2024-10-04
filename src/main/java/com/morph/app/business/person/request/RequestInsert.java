package com.morph.app.business.person.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInsert {
  @NotBlank(message = "El campo \"userName\" es requerido.")
  private String userName;
  @NotBlank(message = "El campo \"password\" es requerido.")
  private String password;
  @NotBlank(message = "El campo \"email\" es requerido.")
  private String email;
}
