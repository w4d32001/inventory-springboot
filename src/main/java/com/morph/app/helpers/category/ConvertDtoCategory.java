package com.morph.app.helpers.category;

import java.text.ParseException;

import com.morph.app.business.category.request.RequestInsert;
import com.morph.app.dto.DtoCategory;

public class ConvertDtoCategory {
  public static DtoCategory convertDtoCategory(RequestInsert sInsert) throws ParseException {
    DtoCategory dtoCategory = new DtoCategory();
    dtoCategory.setName(sInsert.getName());
    dtoCategory.setDescription(sInsert.getDescription());
    return dtoCategory;
  }
}
