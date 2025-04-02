package com.inallmedia.exercise.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inallmedia.exercise.models.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ProductRepository {

    public List<Product> products = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/repository.json");
        try {
            Product[] productArray = objectMapper.readValue(is, Product[].class);
            products = new CopyOnWriteArrayList<>(Arrays.asList(productArray));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Product p : products) {
            System.out.println(p.toString());
        }
    }

    public Optional<List<Product>> findByPriceRange(Integer initialPrice, Integer finalPrice) {
        List<Product> filteredProducts = products.stream().filter(
                product -> product.getPrice() >= initialPrice
                        && product.getPrice() <= finalPrice && 1 == product.getAvailable()).toList();
        return filteredProducts.isEmpty() ? Optional.empty() : Optional.of(filteredProducts);
    }

    public List<Product> findByPriceAsc() {
        return products.stream().sorted(Comparator.comparingInt(Product::getPrice)).filter(product -> 1 == product.getAvailable()).toList();
    }

    public void addProduct(Product product){
        products.add(product);
    }


}
