package com.morph.app.entity;

import java.time.LocalDateTime;

import com.morph.app.dto.DtoOrder;
import com.morph.app.entity.generic.EntityGeneric;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "torder")
public class TOrder extends EntityGeneric {
  @Id
  @Column(length = 36)
  private String orderId;
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private TProduct product;
  @Column(nullable = false, columnDefinition = "INTEGER")
  private int quantity;
  @Column(nullable = false, columnDefinition = "DATE")
  private LocalDateTime orderDate;
  @ManyToOne
  @JoinColumn(name = "supplier_id", nullable = false)
  private TSupplier supplier;

  public TOrder() {
  }

  public TOrder(DtoOrder dtoOrder) {
    this.orderId = dtoOrder.getOrderId();
    this.product = dtoOrder.getProduct();
    this.quantity = dtoOrder.getQuantity();
    this.orderDate = dtoOrder.getOrderDate();
    this.supplier = dtoOrder.getSupplier();
    this.createdAt = dtoOrder.getCreatedAt();
    this.updatedAt = dtoOrder.getUpdatedAt();
  }

}
