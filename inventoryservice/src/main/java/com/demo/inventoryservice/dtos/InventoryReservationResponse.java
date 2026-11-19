package com.demo.inventoryservice.dtos;

import com.demo.inventoryservice.models.enums.AllocatedStatus;

public record InventoryReservationResponse(
	String skuCode,
	Integer requestedQuantity,
	Integer allocatedQuantity,
	Integer unfulfilledQuantity,
	AllocatedStatus status
) {
}
