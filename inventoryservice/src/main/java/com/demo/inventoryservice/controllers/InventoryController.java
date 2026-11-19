package com.demo.inventoryservice.controllers;

import com.demo.inventoryservice.dtos.InventoryRequestDto;
import com.demo.inventoryservice.dtos.InventoryReservationRequest;
import com.demo.inventoryservice.dtos.InventoryReservationResponse;
import com.demo.inventoryservice.dtos.InventoryResponseDto;
import com.demo.inventoryservice.exceptions.ProductNotFoundException;
import com.demo.inventoryservice.models.Inventory;
import com.demo.inventoryservice.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryService inventoryService;

	@GetMapping("/{skuCode}")
	public boolean isInStock(@PathVariable String skuCode){
		
		return inventoryService.isInStock(skuCode);
	}
	
	@GetMapping
	public ResponseEntity<List<InventoryResponseDto>> areInStock(@RequestParam List<String> skuCodes) throws ProductNotFoundException {
		
		List<InventoryResponseDto> inventoryResponseDtos = inventoryService.areInStock(skuCodes);
		return ResponseEntity.status(HttpStatus.OK).body(inventoryResponseDtos);
	}
	
	@PostMapping("/addInventory")
	public ResponseEntity<List<InventoryRequestDto>> addInventory(@RequestBody List<InventoryRequestDto> inventoryRequestDtos){
		
		List<InventoryRequestDto> requestDtos = inventoryService.addInventory(inventoryRequestDtos);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(requestDtos);
	}
	
	@PostMapping("/reserve")
	public List<InventoryReservationResponse> reserveProducts(
		@RequestBody List<InventoryReservationRequest> inventoryReservationRequests) throws ProductNotFoundException{
		
		return inventoryService.reserveProducts(inventoryReservationRequests);
	}
}
