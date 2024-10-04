package com.morph.app.business.person.response;

import java.util.ArrayList;
import java.util.List;

import com.morph.app.business.ResponseGeneral;

public class ResponseGetAll extends ResponseGeneral {
  public class Dto {
    public List<Object> listPerson;
  }

  public Dto dto;

  public ResponseGetAll(){
    dto = new Dto();
    dto.listPerson = new ArrayList<>();
  }
}
