package com.morph.app.helpers.supplier;

import org.apache.el.parser.ParseException;

import com.morph.app.business.supplier.request.RequestInsert;
import com.morph.app.dto.DtoSupplier;

public class ConvertDtoSupplier {
  public static DtoSupplier convertDtoSupplier(RequestInsert soInsert) throws ParseException {
    DtoSupplier dtoSupplier = new DtoSupplier();
    dtoSupplier.setName(soInsert.getName());
    dtoSupplier.setContact(soInsert.getContact());
    dtoSupplier.setPhone(soInsert.getPhone());
    dtoSupplier.setEmail(soInsert.getEmail());
    dtoSupplier.setAddress(soInsert.getAddress());
    return dtoSupplier;
  }
}
