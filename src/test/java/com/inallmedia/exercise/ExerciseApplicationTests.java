package com.inallmedia.exercise;

import com.inallmedia.exercise.models.Product;
import com.inallmedia.exercise.repository.ProductRepository;
import com.inallmedia.exercise.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.ContextLoader;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest
@AutoConfigureMockMvc
class ExerciseApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;

	@Test
	void testGetProductsByPriceRangeOk() throws Exception {
		productRepository.addProduct(new Product("74002423","product1","Accessories",751,12,1));
		productRepository.addProduct(new Product("74002423","product2","Accessories",752,12,1));
		productRepository.addProduct(new Product("74002423","product3","Accessories",753,12,1));
		productRepository.addProduct(new Product("74002423","product4","Accessories",754,12,1));
		productRepository.addProduct(new Product("74002423","product5","Accessories",755,12,1));


		mockMvc.perform(MockMvcRequestBuilders.get("/filter/price/751/755"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].item").value("product1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[4].item").value("product5"));
	}

	@Test
	void testGetProductsByPriceRangeNotFound() throws Exception {
		productRepository.addProduct(new Product("74002423","product1","Accessories",751,12,1));
		productRepository.addProduct(new Product("74002423","product2","Accessories",752,12,1));
		productRepository.addProduct(new Product("74002423","product3","Accessories",753,12,1));
		productRepository.addProduct(new Product("74002423","product4","Accessories",754,12,1));
		productRepository.addProduct(new Product("74002423","product5","Accessories",755,12,1));


		mockMvc.perform(MockMvcRequestBuilders.get("/filter/price/741/745"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testGetProductsByPriceRangeParametersNotOk() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/filter/price/745/741"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void testGetProductsByPriceRangeParameterNegative() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/filter/price/-745/741"))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
