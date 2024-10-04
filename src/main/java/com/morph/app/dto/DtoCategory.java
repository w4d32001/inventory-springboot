package com.morph.app.dto;

import com.morph.app.dto.generic.DtoGeneric;
import com.morph.app.entity.TCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoCategory extends DtoGeneric {
  private String categoryId;
  private String name;
  private String description;

  public DtoCategory() {
  }

  public DtoCategory(TCategory tCategory) {
    this.categoryId = tCategory.getCategoryId();
    this.name = tCategory.getName();
    this.description = tCategory.getDescription();
    this.createdAt = tCategory.getCreatedAt();
    this.updatedAt = tCategory.getUpdatedAt();
  }
}
