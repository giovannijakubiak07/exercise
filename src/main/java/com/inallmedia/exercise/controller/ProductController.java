package com.inallmedia.exercise.controller;

import com.inallmedia.exercise.models.Product;
import com.inallmedia.exercise.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.inallmedia.exercise.utils.ValidationUtils;
import java.util.List;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("filter/price/{initialRange}/{finalRange}")
    public ResponseEntity<List<Product>> filterProducts(@PathVariable Integer initialRange, @PathVariable Integer finalRange){
        if(!ValidationUtils.validateParameters(initialRange, finalRange)){
            return ResponseEntity.badRequest().body(null);
        }
        return productService.retrieveProductsByPriceRange(initialRange, finalRange)
                .map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("sort/price")
    public ResponseEntity<List<Product>> sortedProducs(){
        List<Product> products = productService.retrieveSortedProducts();
        return ResponseEntity.ok(products);
    }

}
