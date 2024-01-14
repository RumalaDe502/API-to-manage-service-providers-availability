
package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controllers.CustomerStreamPickupController;
import com.example.demo.dto.StreamPickupRequest;
import com.example.demo.service.RegisteredStreamPickupService;

@WebMvcTest(CustomerStreamPickupController.class)
public class CustomerStreamPickupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RegisteredStreamPickupService pickupService;

	@Test
	public void testRegisterStreamPickup() throws Exception {
		// Mocking the service behavior
		Mockito.doNothing().when(pickupService).registerStreamPickup(ArgumentMatchers.anyInt(),
				ArgumentMatchers.anyInt(), ArgumentMatchers.any(LocalDate.class));

		// Creating a sample request 
		StreamPickupRequest request = new StreamPickupRequest();
		request.setWasteStreamId(1);
		request.setServiceProviderId(1);
		request.setPickupDate("2023-01-01");

		// Performing the POST request
		mockMvc.perform(post("/seenons/stream-pickup/register").contentType(MediaType.APPLICATION_JSON)
				.content("{\"wasteStreamId\": 1, \"serviceProviderId\": 1, \"pickupDate\": \"2023-01-01\"}")
				.characterEncoding("utf-8")).andExpect(status().isCreated());
	}
}
