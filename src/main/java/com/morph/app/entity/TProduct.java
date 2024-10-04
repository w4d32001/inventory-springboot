package com.morph.app.entity;

import java.util.List;

import java.math.BigDecimal;

import com.morph.app.dto.DtoProduct;
import com.morph.app.entity.generic.EntityGeneric;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Table(name = "tproduct")
public class TProduct extends EntityGeneric {

  @Id
  @Column(length = 36)
  private String productId;
  @Column(length = 50)
  private String name;
  @Column(columnDefinition = "TEXT")
  private String description;
  @Column(precision = 10, scale = 2)
  private BigDecimal price;
  @Column(columnDefinition = "INTEGER")
  private int stock;
  @ManyToOne
  @JoinColumn(name = "supplier_id", nullable = false)
  private TSupplier supplier;
  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private TCategory category;
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<TMovements> movements;
  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<TOrder> orders;

  public TProduct() {}

  public TProduct(DtoProduct dtoProduct) {
    this.productId = dtoProduct.getProductId();
    this.name = dtoProduct.getName();
    this.description = dtoProduct.getDescription();
    this.price = dtoProduct.getPrice();
    this.stock = dtoProduct.getStock();
    this.supplier = dtoProduct.getSupplier();
    this.category = dtoProduct.getCategory();
    this.createdAt = dtoProduct.getCreatedAt();
    this.updatedAt = dtoProduct.getUpdatedAt();
  }

}
