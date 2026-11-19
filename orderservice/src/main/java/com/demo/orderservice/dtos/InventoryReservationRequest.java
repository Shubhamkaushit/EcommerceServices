package com.demo.orderservice.dtos;

public record InventoryReservationRequest(String skuCode, Integer quantity) {
}
