package com.demo.inventoryservice.dtos;

public record InventoryReservationRequest(String skuCode, Integer quantity) {
}
