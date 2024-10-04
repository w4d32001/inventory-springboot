package com.morph.app.business.supplier.response;

import java.util.ArrayList;
import java.util.List;

import com.morph.app.business.ResponseGeneral;

public class ResponseGetAll extends ResponseGeneral {
  public class Dto {
    public List<Object> listSupplier;
  }

  public Dto dto;

  public ResponseGetAll() {
    dto = new Dto();
    dto.listSupplier = new ArrayList<>();
  }
}
