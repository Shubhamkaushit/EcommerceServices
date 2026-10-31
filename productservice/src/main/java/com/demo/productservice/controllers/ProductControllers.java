package com.demo.productservice.controllers;

import com.demo.productservice.dtos.ProductRequestDto;
import com.demo.productservice.dtos.ProductResponseDto;
import com.demo.productservice.exceptions.ProductNotFoundException;
import com.demo.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("productController")
@RequestMapping("/product")
public class ProductControllers {
	
	private final ProductService productServiceImpl;
	
	public ProductControllers(@Qualifier("productServiceImpl") ProductService productServiceImpl) {
		this.productServiceImpl = productServiceImpl;
	}
	
	@PostMapping
	public ResponseEntity<ProductRequestDto> createProduct(@RequestBody ProductRequestDto productRequestDto){
		
		ProductRequestDto savedProduct = productServiceImpl.createProduct(productRequestDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDto> getProductById(@PathVariable String id) throws ProductNotFoundException{
		
		ProductResponseDto receivedProduct = productServiceImpl.getProductById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(receivedProduct);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductResponseDto> getAllProducts() throws ProductNotFoundException {
		
		return productServiceImpl.getAllProducts();
	}
}
