package com.morph.app.entity;

import java.util.List;

import com.morph.app.dto.DtoSupplier;
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
@Table(name = "tsupplier")
public class TSupplier extends EntityGeneric {
  @Id
  @Column(length = 36)
  private String supplierId;
  @Column(length = 50, nullable = false)
  private String name;
  @Column(length = 100, nullable = false)
  private String contact;
  @Column(length = 15, nullable = false)
  private String phone;
  @Column(length = 100, nullable = false)
  private String email;
  @Column(length = 255)
  private String address;
  @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<TProduct> products;
  @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<TOrder> orders;
  public TSupplier() {}

  public TSupplier(DtoSupplier dtoSupplier) {
    this.supplierId = dtoSupplier.getSupplierId();
     this.name = dtoSupplier.getName();
     this.contact = dtoSupplier.getContact();
     this.phone = dtoSupplier.getPhone();
     this.email = dtoSupplier.getEmail();
     this.address = dtoSupplier.getAddress();
     this.createdAt = dtoSupplier.getCreatedAt();
     this.updatedAt = dtoSupplier.getUpdatedAt();
  }

}
