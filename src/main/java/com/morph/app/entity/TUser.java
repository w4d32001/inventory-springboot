package com.morph.app.entity;

import java.util.List;

import com.morph.app.dto.DtoUser;
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
@Table(name = "tuser")
public class TUser extends EntityGeneric{
  @Id
  @Column(length = 36)
  private String userId;
  @Column(length = 50, nullable = false)
  private String userName;
  @Column(nullable = false)
  private String password;
  @Column(length = 150, nullable = false)
  private String email;
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  public List<TMovements> movements;

  public TUser(){}

  public TUser(DtoUser dtoUser){
    this.userId = dtoUser.getUserId();
    this.userName = dtoUser.getUserName();
    this.password = dtoUser.getPassword();
    this.email = dtoUser.getEmail();
    this.createdAt = dtoUser.getCreatedAt();
    this.updatedAt = dtoUser.getUpdatedAt();
  }
}
