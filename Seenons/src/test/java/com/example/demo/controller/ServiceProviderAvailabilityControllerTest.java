
package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.ServiceProviderAvailabilityController;
import com.example.demo.dto.AvailableServiceProviderResp;
import com.example.demo.service.ServiceProviderService;

@WebMvcTest(ServiceProviderAvailabilityController.class)
public class ServiceProviderAvailabilityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ServiceProviderService serviceProviderService;

	@Test
	public void testGetAvailableServiceProviders() throws Exception {
		// Mocking the service behavior
		Mockito.when(serviceProviderService.getAvailableServiceProviders(ArgumentMatchers.anyInt(),
				ArgumentMatchers.any(LocalDate.class))).thenReturn(List.of(new AvailableServiceProviderResp()));

		// Performing the GET request
		mockMvc.perform(get("/seenons/service-providers/availability").param("postalCode1", "12345")
				.param("date", "2023-01-01").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8"))
				.andExpect(status().isOk());
	}
}
