package com.example.tptekup.MvcControllers;

import com.example.tptekup.Entities.Product;
import com.example.tptekup.Services.ProductService;
import com.example.tptekup.Services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    BrandService brandService;
    @RequestMapping("/product/add")
    public String addProduct(Model model){
        Product product=new Product();
        model.addAttribute(("ProductForm"), product);
        model.addAttribute("brands", brandService.getAllBrands());
        return "product/new_product";
    }
    @RequestMapping(value = "/product/save",method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("ProductForm") Product product){
        productService.CreateProd(product);
        return "redirect:/product/list";
    }
    @RequestMapping("/product/list")
    public String listProduct(Model model){
        List<Product> listProducts = productService.getAll();
        model.addAttribute("listProducts",listProducts);
        return "Product/productlist";

    }

    @GetMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productService.DeleteProd(id);
        return "redirect:/product/list";
    }
    //    Update
    @GetMapping("prodcut/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        Product product = productService.GetbyId(id);
        model.addAttribute("product", product);

        return "update_product";
    }

    @PostMapping("product/update/{id}")
    public String updateProduct(@PathVariable("id") long id, Product product){

        productService.updateProd(product);
        return "redirect:/product/list";
    }

}
