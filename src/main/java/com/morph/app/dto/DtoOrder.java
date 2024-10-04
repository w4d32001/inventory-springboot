package com.morph.app.dto;

import java.time.LocalDateTime;

import com.morph.app.dto.generic.DtoGeneric;
import com.morph.app.entity.TOrder;
import com.morph.app.entity.TProduct;
import com.morph.app.entity.TSupplier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoOrder extends DtoGeneric {
  private String orderId;
  public TProduct product;
  private int quantity;
  private LocalDateTime orderDate;
  public TSupplier supplier;

  public DtoOrder() {}

  public DtoOrder(TOrder order) {
    this.orderId = order.getOrderId();
    this.product = order.getProduct();
    this.quantity = order.getQuantity();
    this.orderDate = order.getOrderDate();
    this.supplier = order.getSupplier();
    this.createdAt = order.getCreatedAt();  
    this.updatedAt = order.getUpdatedAt();
  }

}
