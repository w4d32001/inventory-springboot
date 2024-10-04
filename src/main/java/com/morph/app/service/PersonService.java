package com.morph.app.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morph.app.dto.DtoUser;
import com.morph.app.entity.TUser;
import com.morph.app.repository.PersonRepository;

@Service
public class PersonService {
  @Autowired
  private PersonRepository personRepository;

  public List<DtoUser> getAll(){
    return this.personRepository.findAll().stream()
          .map(DtoUser::new)
          .collect(Collectors.toList());
  }

  public boolean insert(DtoUser dtoUser){
    dtoUser.setUserId(UUID.randomUUID().toString());
    dtoUser.setCreatedAt(new Date());
    dtoUser.setUpdatedAt(new Date());
    TUser tUser = new TUser(dtoUser);
    personRepository.save(tUser);
    return true;
  }

  public boolean delete(String id){
    if(personRepository.existsById(id)){
      personRepository.deleteById(id);
      return true;
    }else{
      return false;
    }
  }

  public boolean update(DtoUser dtoUser){
    dtoUser.setUpdatedAt(new Date());

    return personRepository.findById(dtoUser.getUserId()).map(tUser -> {
      tUser.setUserName(dtoUser.getUserName());
      tUser.setEmail(dtoUser.getEmail());
      tUser.setUpdatedAt(dtoUser.getUpdatedAt());

      personRepository.save(tUser);

      return true;
    }).orElse(false);
  }

}
