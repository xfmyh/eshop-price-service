package com.roncoo.eshop.price.web.controller;


import com.roncoo.eshop.price.model.ProductPrice;
import com.roncoo.eshop.price.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product-price")
public class ProductPriceController {

    @Autowired
    private ProductPriceService productPriceService;


    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(ProductPrice productPrice) {
        try {
            productPriceService.add(productPrice);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(ProductPrice productPrice) {
        try {
            productPriceService.update(productPrice);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Long id) {
        try {
            productPriceService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/findById")
    @ResponseBody
    public ProductPrice findById(Long id) {
        try {
            return productPriceService.findById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ProductPrice();
    }


    @RequestMapping(value = "/findByProductId")
    @ResponseBody
    public ProductPrice findByProductId(Long productId) {
        try {
            return productPriceService.findByProductId(productId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ProductPrice();
    }
}
