package com.example.tptekup.controllers;

import com.example.tptekup.Entities.Brand;
import com.example.tptekup.Services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BrandController {
    @Autowired
    BrandService brandService;

    @RequestMapping("/addBrand")
    public String addBrand(Model model) {
        Brand brand = new Brand();
        model.addAttribute("BrandForm", brand);
        return "brandnew";
    }

    @RequestMapping(value = "/saveBrand", method = RequestMethod.POST)
    public String saveBrand(@ModelAttribute("BrandForm") Brand brand) {
        brandService.createBrand(brand);
        return "redirect:/allBrands";
    }

    @RequestMapping("/allBrands")
    public String listBrands(Model model) {
        List<Brand> listBrands = brandService.getAllBrands();
        model.addAttribute("listBrands", listBrands);
        return "list_brands";
    }

    @GetMapping("deleteBrand/{id}")
    public String deleteBrand(@PathVariable("id") long id) {
        brandService.deleteBrand(id);
        return "redirect:/allBrands";
    }

    @GetMapping("editBrand/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Brand brand = brandService.getBrandById(id);
        model.addAttribute("brand", brand);
        return "update_brand";
    }

    @PostMapping("updateBrand/{id}")
    public String updateBrand(@PathVariable("id") long id, Brand brand) {
        brandService.updateBrand(brand);
        return "redirect:/allBrands";
    }
}
