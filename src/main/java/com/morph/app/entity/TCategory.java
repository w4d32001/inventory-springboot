package com.morph.app.entity;

import java.util.List;

import com.morph.app.dto.DtoCategory;
import com.morph.app.entity.generic.EntityGeneric;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tcategory")
public class TCategory extends EntityGeneric {
  @Id
  @Column(length = 36)
  private String categoryId;
  @Column(length = 50, nullable = false)
  private String name;
  @Column(length = 255)
  private String description;
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<TProduct> products;

  public TCategory() {}

  public TCategory(DtoCategory dtoCategory) {
    this.categoryId = dtoCategory.getCategoryId();
    this.name = dtoCategory.getName();
    this.description = dtoCategory.getDescription();
    this.createdAt = dtoCategory.getCreatedAt();
    this.updatedAt = dtoCategory.getUpdatedAt();
  }

}
