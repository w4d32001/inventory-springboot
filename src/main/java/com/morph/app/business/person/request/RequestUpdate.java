package com.morph.app.business.person.request;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class RequestUpdate {
  private String userId;
  private String userName;
  private String email;
}
