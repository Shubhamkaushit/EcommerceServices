package com.demo.orderservice.models;

import com.demo.orderservice.models.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String orderNumber;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<OrderItem> orderItemList;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
}
