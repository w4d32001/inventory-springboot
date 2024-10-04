package com.morph.app.dto.generic;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public abstract class DtoGeneric {
  protected Date createdAt;
  protected Date updatedAt;
}
