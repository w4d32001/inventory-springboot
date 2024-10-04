package com.morph.app.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morph.app.dto.DtoSupplier;
import com.morph.app.entity.TSupplier;
import com.morph.app.repository.SupplierRepository;

@Service
public class SupplierService {
  @Autowired
  private SupplierRepository supplierRepository;
  
  public List<DtoSupplier> getAll(){
    return this.supplierRepository.findAll().stream().map(DtoSupplier::new).collect(Collectors.toList());
  }

  public boolean insert(DtoSupplier dtoSupplier) {
    dtoSupplier.setSupplierId(UUID.randomUUID().toString());
    dtoSupplier.setCreatedAt(new Date());
    dtoSupplier.setUpdatedAt(new Date());
    TSupplier supplier = new TSupplier(dtoSupplier);
    supplierRepository.save(supplier);
    return true;
  }

  public boolean delete(String supplierId){
    if(supplierRepository.existsById(supplierId)){
      supplierRepository.deleteById(supplierId);
      return true;
    }else{
      return false;
    }
  }

  public boolean update(DtoSupplier dtoSupplier){
    dtoSupplier.setUpdatedAt(new Date());
    return supplierRepository.findById(dtoSupplier.getSupplierId()).map(tSupplier -> {
      tSupplier.setName(dtoSupplier.getName());
      tSupplier.setAddress(dtoSupplier.getAddress());
      tSupplier.setName(dtoSupplier.getName());
      tSupplier.setContact(dtoSupplier.getContact());
      tSupplier.setPhone(dtoSupplier.getPhone());
      supplierRepository.save(tSupplier);
      return true;
    }).orElse(false);
  }

}
