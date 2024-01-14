package com.example.demo.controllers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StreamPickupRequest;
import com.example.demo.service.RegisteredStreamPickupService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping(path = "/seenons")
@OpenAPIDefinition(info = @Info(title = "Customer Stream Pickup Controller",
description = " Controller to register the collection of a waste stream to be performed by a service provider"))
public class CustomerStreamPickupController {

	private final RegisteredStreamPickupService pickupService;

	public CustomerStreamPickupController(RegisteredStreamPickupService pickupService) {
		this.pickupService = pickupService;
	}

	@PostMapping("/stream-pickup/register")
	@ApiOperation("Register Stream Pickup by Sevice Provider.")
	public ResponseEntity<String> registerStreamPickup(@RequestBody StreamPickupRequest request) {
		LocalDate pickupDate = LocalDate.parse(request.getPickupDate());
		pickupService.registerStreamPickup(request.getWasteStreamId(), request.getServiceProviderId(), pickupDate);
		return new ResponseEntity<>("Stream pickup registered successfully", HttpStatus.CREATED);
	}
}
