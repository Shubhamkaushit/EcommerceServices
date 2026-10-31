package com.demo.productservice.services;

import com.demo.productservice.dtos.ProductRequestDto;
import com.demo.productservice.dtos.ProductResponseDto;
import com.demo.productservice.exceptions.ProductNotFoundException;
import com.demo.productservice.models.Product;
import com.demo.productservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("productServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	private final ProductRepository productRepository;
	
	@Override
	public ProductRequestDto createProduct(ProductRequestDto productRequestDto) {
		
		Product product = Product.builder()
			.pname(productRequestDto.getPname())
			.description(productRequestDto.getDescription())
			.price(productRequestDto.getPrice())
			.build();
		
		log.info("Product {} is saved", product.getId());
		return convertToRequestDto(productRepository.save(product));
	}
	
	@Override
	public ProductResponseDto getProductById(String id) throws ProductNotFoundException {
		
		ProductResponseDto responseDto = new ProductResponseDto();
		if (productRepository.findById(id).isPresent()) {
			responseDto = converToResponseDto(productRepository.findById(id).get());
		}
		else {
			String message = "The product with id "+id+" was not found";
			throw new ProductNotFoundException(message);
		}
		
		return responseDto;
	}
	
	public List<ProductResponseDto> getAllProducts() throws ProductNotFoundException {
		
		List<ProductResponseDto> responseDtoList = new ArrayList<>();
		List<Product> products = productRepository.findAll();
		
		if (products.isEmpty()) throw new ProductNotFoundException("No Product present in the Database");
		
		for(Product product: products){
			responseDtoList.add(converToResponseDto(product));
		}
		
		return responseDtoList;
	}
	
	public ProductRequestDto convertToRequestDto(Product product) {
		
		ProductRequestDto productRequestDto = new ProductRequestDto();
		productRequestDto.setDescription(product.getDescription());
		productRequestDto.setPname(product.getPname());
		productRequestDto.setPrice(product.getPrice());
		return productRequestDto;
	}
	
	public ProductResponseDto converToResponseDto(Product product) {
		
		ProductResponseDto productResponseDto = new ProductResponseDto();
		productResponseDto.setId(product.getId());
		productResponseDto.setPname(product.getPname());
		productResponseDto.setDescription(product.getDescription());
		productResponseDto.setPrice(product.getPrice());
		return productResponseDto;
	}
}
