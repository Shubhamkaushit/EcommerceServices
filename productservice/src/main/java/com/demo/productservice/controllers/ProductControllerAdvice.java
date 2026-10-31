package com.demo.productservice.controllers;

import com.demo.productservice.dtos.ExceptionDto;
import com.demo.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductControllerAdvice {

	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ExceptionDto handleProductNotFoundException(ProductNotFoundException productNotFoundException){
		
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setHttpStatus(HttpStatus.NOT_FOUND);
		exceptionDto.setMessage(productNotFoundException.getMessage());
		
		return exceptionDto;
	}
}
