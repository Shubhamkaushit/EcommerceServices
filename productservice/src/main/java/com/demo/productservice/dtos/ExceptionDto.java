package com.demo.productservice.dtos;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionDto {
	HttpStatus httpStatus;
	String message;
}
