package com.inallmedia.exercise.services;

import com.inallmedia.exercise.models.Product;
import com.inallmedia.exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public Optional<List<Product>> retrieveProductsByPriceRange(Integer initialRange, Integer finalRange) {
        return productRepository.findByPriceRange(initialRange, finalRange);
    }


    public List<Product> retrieveSortedProducts() {
        return productRepository.findByPriceAsc();
    }
}
