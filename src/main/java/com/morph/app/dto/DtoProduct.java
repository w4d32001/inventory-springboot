package com.morph.app.dto;

import com.morph.app.dto.generic.DtoGeneric;
import com.morph.app.entity.TCategory;
import com.morph.app.entity.TProduct;
import com.morph.app.entity.TSupplier;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoProduct extends DtoGeneric {
  private String productId;
  private String name;
  private String description;
  private BigDecimal  price;
  private int stock;
  private TSupplier supplier;
  private TCategory category;

  public DtoProduct() {}

  public DtoProduct(TProduct tProduct){
    this.productId = tProduct.getProductId();
     this.name = tProduct.getName();
     this.description = tProduct.getDescription();
     this.price = tProduct.getPrice();
     this.stock = tProduct.getStock();
     this.supplier = tProduct.getSupplier();
     this.category = tProduct.getCategory();
     this.createdAt = tProduct.getCreatedAt();
     this.updatedAt = tProduct.getUpdatedAt();
  }


}
