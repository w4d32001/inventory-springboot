package com.morph.app.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.morph.app.dto.DtoProduct;
import com.morph.app.entity.TProduct;
import com.morph.app.repository.CategoryRepository;
import com.morph.app.repository.ProductRepository;
import com.morph.app.repository.SupplierRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  @Autowired
  private SupplierRepository supplierRepository;

  public List<DtoProduct> getAll() {
    return this.productRepository.findAll().stream().map(DtoProduct::new).collect(Collectors.toList());
  }

  public boolean insert(DtoProduct dtoProduct) {
    if (!this.supplierRepository.existsById(dtoProduct.getSupplier().getSupplierId())) {
      return false;
    }
    if (!this.categoryRepository.existsById(dtoProduct.getCategory().getCategoryId())) {
      return false;
    }
    dtoProduct.setProductId(UUID.randomUUID().toString());
    dtoProduct.setCreatedAt(new Date());
    dtoProduct.setUpdatedAt(new Date());
    TProduct tProduct = new TProduct(dtoProduct);
    this.productRepository.save(tProduct);
    return true;
  }

  public boolean delete(String productId) {
    if (this.productRepository.existsById(productId)) {
      this.productRepository.deleteById(productId);
      return true;
    } else {
      return false;
    }
  }

  public boolean update(DtoProduct dtoProduct) {
    if (!this.supplierRepository.existsById(dtoProduct.getSupplier().getSupplierId())) {
      return false;
    }
    if (!this.categoryRepository.existsById(dtoProduct.getCategory().getCategoryId())) {
      return false;
    }
    dtoProduct.setUpdatedAt(new Date());
    return this.productRepository.findById(dtoProduct.getProductId()).map(
        tProduct -> {
          tProduct.setName(dtoProduct.getName());
          tProduct.setDescription(dtoProduct.getDescription());
          tProduct.setPrice(dtoProduct.getPrice());
          tProduct.setStock(dtoProduct.getStock());
          tProduct.setSupplier(dtoProduct.getSupplier());
          tProduct.setCategory(dtoProduct.getCategory());
          productRepository.save(tProduct);
          return true;
        }).orElse(false);
  }

}
