package com.demo.inventoryservice.services;

import com.demo.inventoryservice.dtos.InventoryRequestDto;
import com.demo.inventoryservice.dtos.InventoryReservationRequest;
import com.demo.inventoryservice.dtos.InventoryReservationResponse;
import com.demo.inventoryservice.dtos.InventoryResponseDto;
import com.demo.inventoryservice.exceptions.ProductNotFoundException;
import com.demo.inventoryservice.models.Inventory;
import com.demo.inventoryservice.models.enums.AllocatedStatus;
import com.demo.inventoryservice.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("inventoryService")
@RequiredArgsConstructor
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	public boolean isInStock(String skuCode){
		
		Optional<Inventory> inventory = inventoryRepository.findBySkuCode(skuCode);
		return inventory.isPresent() && inventory.get().getQuantity() > 0;
	}
	
	public List<InventoryResponseDto> areInStock(List<String> skuCodes) throws ProductNotFoundException {
	
		List<InventoryResponseDto> inventoryResponseDtos = inventoryRepository.findBySkuCodeIn(skuCodes).stream()
			.map(inventory -> InventoryResponseDto.builder()
				.skuCode(inventory.getSkuCode())
				.isInStock(inventory.getQuantity() > 0).build()
			).toList();
		
		return inventoryResponseDtos;
	}
	
	public List<InventoryRequestDto> addInventory(List<InventoryRequestDto> inventoryRequestDtos){
		
		for(InventoryRequestDto inventoryRequestDto: inventoryRequestDtos){
			Inventory inventory = new Inventory();
			inventory.setQuantity(inventoryRequestDto.getQuantity());
			inventory.setSkuCode(inventoryRequestDto.getSkuCode());
			inventoryRepository.save(inventory);
		}
		return inventoryRequestDtos;
	}
	
	@SneakyThrows
	@Transactional(rollbackFor = ProductNotFoundException.class)
	public List<InventoryReservationResponse> reserveProducts(
		List<InventoryReservationRequest> inventoryReservationRequests){
	
		return inventoryReservationRequests.stream().map(this::reserveProduct).toList();
	}
	
	protected InventoryReservationResponse reserveProduct(InventoryReservationRequest request){
	
		Inventory inventory = inventoryRepository.findBySkuCode(request.skuCode())
			.orElseThrow(() -> new ProductNotFoundException("SkuCode " + request.skuCode() + " doesn't exist"));
		
		int availableQuantity = inventory.getQuantity();
		if (availableQuantity == 0){
			return new InventoryReservationResponse(
				request.skuCode(), request.quantity(), 0, request.quantity(), AllocatedStatus.OUT_OF_STOCK
			);
		}
		
		int allocatedQuantity = Math.min(request.quantity(), availableQuantity);
		
		inventory.setQuantity(availableQuantity - allocatedQuantity);
		
		int unfulfilledQuantity = request.quantity() - allocatedQuantity;
		
		AllocatedStatus status = unfulfilledQuantity > 0 ? AllocatedStatus.PARTIALLY_ALLOCATED : AllocatedStatus.FULLY_ALLOCATED;
		
		inventoryRepository.save(inventory);
		
		return new InventoryReservationResponse(
			request.skuCode(), request.quantity(), allocatedQuantity, unfulfilledQuantity, status
		);
	}
}
