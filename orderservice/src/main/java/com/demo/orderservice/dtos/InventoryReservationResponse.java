package com.demo.orderservice.dtos;


import com.demo.orderservice.models.enums.AllocatedStatus;

public record InventoryReservationResponse(
	String skuCode,
	Integer requestedQuantity,
	Integer allocatedQuantity,
	Integer unfulfilledQuantity,
	AllocatedStatus status
) {
}
