package com.demo.inventoryservice.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class InventoryRequestDto {
	
	private Long id;
	
	private String skuCode;
	
	private int quantity;
}
