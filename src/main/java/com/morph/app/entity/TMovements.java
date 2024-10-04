package com.morph.app.entity;

import java.time.LocalDateTime;

import com.morph.app.dto.DtoMovements;
import com.morph.app.entity.generic.EntityGeneric;
import com.morph.app.helpers.ProductStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tmovements")
public class TMovements extends EntityGeneric {

  @Id
  @Column(length = 36)
  private String movementId;
  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private TProduct product;
  @Column(columnDefinition = "INTEGER", nullable = false)
  private int quantity;
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ProductStatus type;
  @Column(length = 255)
  private String description;
  @Column(columnDefinition = "DATE", nullable = false)
  private LocalDateTime date;
  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private TUser user;

  public TMovements() {
  }

  public TMovements(DtoMovements dtoMovements) {
    this.movementId = dtoMovements.getMovementId();
    this.product = dtoMovements.getProduct();
    this.quantity = dtoMovements.getQuantity();
    this.type = dtoMovements.getType();
    this.description = dtoMovements.getDescription();
    this.date = dtoMovements.getDate();
    this.user = dtoMovements.getUser();
    this.createdAt = dtoMovements.getCreatedAt();
    this.updatedAt = dtoMovements.getUpdatedAt();
  }

}
