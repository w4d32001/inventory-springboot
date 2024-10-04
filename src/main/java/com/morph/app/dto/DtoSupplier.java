package com.morph.app.dto;

import com.morph.app.dto.generic.DtoGeneric;
import com.morph.app.entity.TSupplier;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class DtoSupplier extends DtoGeneric {

  private String supplierId;
  private String name;
  private String contact;
  private String phone;
  private String email;
  private String address;

  public DtoSupplier() {}

  public DtoSupplier(TSupplier tSupplier) {
    this.supplierId = tSupplier.getSupplierId();
    this.name = tSupplier.getName();
    this.contact = tSupplier.getContact();
    this.phone = tSupplier.getPhone();
    this.email = tSupplier.getEmail();
    this.address = tSupplier.getAddress();
    this.createdAt = tSupplier.getCreatedAt();
    this.updatedAt = tSupplier.getUpdatedAt();
  }

}
