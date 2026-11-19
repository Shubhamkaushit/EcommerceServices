package com.demo.orderservice.controllers;

import com.demo.orderservice.dtos.ExceptionDto;
import com.demo.orderservice.exceptions.ProductNotInStockException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderControllerAdvice {
	
	@ExceptionHandler(ProductNotInStockException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ExceptionDto handleProductNotInStockException(ProductNotInStockException exception){
		
		ExceptionDto exceptionDto = new ExceptionDto();
		exceptionDto.setStatus(HttpStatus.NOT_FOUND);
		exceptionDto.setMessage(exception.getMessage());
		return exceptionDto;
	}
}
