package com.demo.orderservice.services;

import com.demo.orderservice.dtos.*;
import com.demo.orderservice.exceptions.ProductNotInStockException;
import com.demo.orderservice.models.Order;
import com.demo.orderservice.models.OrderItem;
import com.demo.orderservice.models.enums.AllocatedStatus;
import com.demo.orderservice.models.enums.OrderStatus;
import com.demo.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service("orderService")
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClient;
	
	public void placeOrder(OrderRequestDto orderRequestDto) throws ProductNotInStockException{
		
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderItem> orderItemList = orderRequestDto.getOrderItemsDtoList()
			.stream()
			.map(this::mapToDto)
			.toList();
		
		order.setOrderItemList(orderItemList);
		
		for (OrderItem orderItem : orderItemList) {
			
			// Call Inventory Service and place order if it is in stock
			Boolean result = webClient.build().get()
				.uri("/inventory/{skuCode}", orderItem.getSkuCode())
				.retrieve()
				.bodyToMono(Boolean.class)
				.block();
			
			if (!Boolean.TRUE.equals(result))
				throw new ProductNotInStockException("Product is not in Stock");
		}
		orderRepository.save(order);
	}
	
	public PlaceOrderResponse placeCompleteOrder(OrderRequestDto orderRequestDto) throws ProductNotInStockException {
		
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderItem> orderItemList = orderRequestDto.getOrderItemsDtoList().stream()
			.map(this::mapToDto).toList();
		
		List<InventoryReservationRequest> requests = orderItemList.stream()
			.map(item -> new InventoryReservationRequest(
				item.getSkuCode(), item.getQuantity()
			))
			.toList();
		
		List<InventoryReservationResponse> responses = webClient.build().post()
			.uri("/inventory/reserve")
			.bodyValue(requests)
			.retrieve()
			.bodyToFlux(InventoryReservationResponse.class)
			.collectList()
			.block();
		
		if (responses == null || responses.isEmpty()){
			throw new ProductNotInStockException(
				"Unable to reserve inventory");
		}
		
		boolean anyAllocated = responses.stream().anyMatch(r -> r.allocatedQuantity() > 0);
		boolean allAllocated = responses.stream().allMatch(r -> r.status() == AllocatedStatus.FULLY_ALLOCATED);
		
		if (!anyAllocated) throw new ProductNotInStockException("None of the requested Products are in Stock");
		else if (allAllocated) order.setOrderStatus(OrderStatus.COMPLETED);
		else order.setOrderStatus(OrderStatus.PARTIAL);
		
		List<OrderItem> allocatedItems = responses.stream()
			.filter(r -> r.allocatedQuantity() > 0)
			.map(r -> {
				OrderItem orderItem = new OrderItem();
				orderItem.setSkuCode(r.skuCode());
				orderItem.setQuantity(r.allocatedQuantity());
				return orderItem;
			})
			.toList();
		
		order.setOrderItemList(allocatedItems);
		orderRepository.save(order);
		
		return new PlaceOrderResponse(
			order.getOrderNumber(),
			order.getOrderStatus(),
			responses,
			allAllocated ? "Order Placed Successfully" : "Order Placed Partially"
		);
	}
	
	public void deleteOrder(Long id) {
		
		orderRepository.deleteById(id);
	}
	
	public void deleteAllOrders() {
		
		orderRepository.deleteAll();
	}
	
	public OrderItem mapToDto(OrderItemsDto orderItemsDto) {
		
		OrderItem orderItem = new OrderItem();
		
		orderItem.setSkuCode(orderItemsDto.getSkuCode());
		orderItem.setPrice(orderItemsDto.getPrice());
		orderItem.setQuantity(orderItemsDto.getQuantity());
		
		return orderItem;
	}
}
