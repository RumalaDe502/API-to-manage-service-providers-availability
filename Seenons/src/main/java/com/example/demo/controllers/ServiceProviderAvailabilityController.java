package com.example.demo.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AvailableServiceProviderResp;
import com.example.demo.service.ServiceProviderService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@RestController
@RequestMapping(path = "/seenons")
@OpenAPIDefinition(info = @Info(title = "Service Provider Availability Controller",
description = " Controller to retrieve availability of service providers for a given location and date."))
public class ServiceProviderAvailabilityController {

	private final ServiceProviderService serviceProviderService;

	public ServiceProviderAvailabilityController(ServiceProviderService serviceProviderService) {
		this.serviceProviderService = serviceProviderService;
	}

	@GetMapping("/service-providers/availability")
	@ApiOperation("Get Available Service Provider for a particular location and date.")
	public List<AvailableServiceProviderResp> getAvailableServiceProviders(@RequestParam int postalCode1,
			@RequestParam LocalDate date) {
		return serviceProviderService.getAvailableServiceProviders(postalCode1, date);
	}
}
