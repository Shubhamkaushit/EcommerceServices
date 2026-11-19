package com.demo.orderservice.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionDto {
	
	private HttpStatus status;
	private String message;
}
