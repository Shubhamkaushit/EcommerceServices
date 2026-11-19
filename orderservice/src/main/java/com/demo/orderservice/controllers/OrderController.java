package com.demo.orderservice.controllers;

import com.demo.orderservice.dtos.OrderRequestDto;
import com.demo.orderservice.dtos.PlaceOrderResponse;
import com.demo.orderservice.exceptions.ProductNotInStockException;
import com.demo.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;

	@PostMapping("/placeOrder")
	@ResponseStatus(HttpStatus.CREATED)
	public String placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws ProductNotInStockException{
		
		orderService.placeOrder(orderRequestDto);
		return "Order placed successfully";
	}
	
	@PostMapping("/placeCompleteOrder")
	@ResponseStatus(HttpStatus.CREATED)
	public PlaceOrderResponse placeCompleteOrder(@RequestBody OrderRequestDto orderRequestDto) throws ProductNotInStockException {
		
		return orderService.placeCompleteOrder(orderRequestDto);
	}
	
	@DeleteMapping("/{id}")
	public String deleteOrder(@PathVariable Long id){
		
		orderService.deleteOrder(id);
		return "Order Deleted Successfully";
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAllOrders(){
		
		orderService.deleteAllOrders();
		return "All Orders Deleted";
	}
}


/*{
	"orderItemsDtoList": [
	{ "skuCode": "oneplus_12",       "price": 799.00,  "quantity": 1 },
	{ "skuCode": "pixel_8_pro",      "price": 999.00,  "quantity": 2 },
	{ "skuCode": "lenovo_thinkpad",  "price": 1150.25, "quantity": 1 },
	{ "skuCode": "hp_spectre_x360",  "price": 1399.99, "quantity": 1 },
	{ "skuCode": "bose_qc_ultra",    "price": 429.00,  "quantity": 2 },
	{ "skuCode": "galaxy_tab_s9",    "price": 849.50,  "quantity": 1 },
	{ "skuCode": "anker_powerbank",  "price": 59.99,   "quantity": 6 },
	{ "skuCode": "razer_deathadder", "price": 69.00,   "quantity": 3 },
	{ "skuCode": "fitbit_charge_6",  "price": 159.95,  "quantity": 2 },
	{ "skuCode": "canon_eos_r50",    "price": 679.00,  "quantity": 1 },
	{ "skuCode": "samsung_ssd_1tb",  "price": 89.99,   "quantity": 4 },
	{ "skuCode": "tplink_router_ax", "price": 119.00,  "quantity": 2 }
	]
}*/
