package com.morph.app.dto;

import com.morph.app.dto.generic.DtoGeneric;
import com.morph.app.entity.TUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoUser extends DtoGeneric {
  private String userId;
  private String userName;
  private String password;
  private String email;

  public DtoUser(){}

  public DtoUser(TUser tUser){
    this.userId = tUser.getUserId();
    this.userName = tUser.getUserName();
    this.password = tUser.getPassword();
    this.email = tUser.getEmail();
    this.createdAt = tUser.getCreatedAt();
    this.updatedAt = tUser.getUpdatedAt();
  }
}
