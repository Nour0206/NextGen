package com.example.tptekup.Services;

import com.example.tptekup.Entities.Brand;
import com.example.tptekup.Repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public Brand createBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand getBrandById(long id) {
        return brandRepository.findById(id).get();
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }

    public Brand updateBrand(Brand brand) {
        return brandRepository.saveAndFlush(brand);
    }
}
