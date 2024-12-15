package com.example.tptekup.Services;

import com.example.tptekup.Entities.Product;
import com.example.tptekup.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public Product CreateProd(Product product){return productRepository.save(product);}
    public Product GetbyId(long id){return productRepository.findById(id).get();}
    public List<Product> getAll(){return productRepository.findAll();}
    public void DeleteProd(long id){ productRepository.deleteById(id);}
    public Product updateProd(Product product){ return  productRepository.saveAndFlush(product);}
}
