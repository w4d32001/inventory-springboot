package com.morph.app.entity.generic;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EntityGeneric {
  @Column()
  protected Date createdAt;
  @Column()
  protected Date updatedAt;
}
