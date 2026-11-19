package com.demo.orderservice.dtos;

import com.demo.orderservice.models.enums.OrderStatus;

import java.util.List;

public record PlaceOrderResponse(
	String orderNumber,
	OrderStatus orderStatus,
	List<InventoryReservationResponse> items,
	String message) {
}
