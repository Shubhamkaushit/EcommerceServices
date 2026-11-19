package com.demo.identityservice.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String keyCloakUserId;
	
	@Column(unique = true)
	private String userName;
	
	@Column(unique = true)
	private String email;
	
	private String firstName;
	
	private String lastName;
}
