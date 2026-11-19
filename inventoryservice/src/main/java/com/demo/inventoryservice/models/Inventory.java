package com.demo.inventoryservice.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Table(name = "t_inventory")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inventory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String skuCode;
	
	private int quantity;
}
