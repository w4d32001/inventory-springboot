package com.morph.app.dto;

import java.time.LocalDateTime;
import com.morph.app.dto.generic.DtoGeneric;
import com.morph.app.entity.TMovements;
import com.morph.app.entity.TProduct;
import com.morph.app.entity.TUser;
import com.morph.app.helpers.ProductStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoMovements extends DtoGeneric {
  private String movementId;
  private TProduct product;
  private int quantity;
  private ProductStatus type;
  private String description;
  private LocalDateTime date;
  private TUser user;

  public DtoMovements() {}

  public DtoMovements(TMovements movement) {
    this.movementId = movement.getMovementId();
    this.product = movement.getProduct();
    this.quantity = movement.getQuantity();
    this.type = movement.getType();
    this.description = movement.getDescription();
    this.date = movement.getDate();
    this.user = movement.getUser();
    this.createdAt = movement.getCreatedAt();
    this.updatedAt = movement.getUpdatedAt();
  }

}
