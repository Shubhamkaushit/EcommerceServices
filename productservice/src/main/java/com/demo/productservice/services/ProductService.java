package com.demo.productservice.services;

import com.demo.productservice.dtos.ProductRequestDto;
import com.demo.productservice.dtos.ProductResponseDto;
import com.demo.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
	
	ProductRequestDto createProduct(ProductRequestDto productRequestDto);
	
	ProductResponseDto getProductById(String id) throws ProductNotFoundException;
	
	List<ProductResponseDto> getAllProducts() throws ProductNotFoundException;
}
