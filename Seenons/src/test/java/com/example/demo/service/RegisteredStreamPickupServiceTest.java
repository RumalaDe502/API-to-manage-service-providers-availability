package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.RegisterStreamPickup;
import com.example.demo.entities.ServiceProvider;
import com.example.demo.entities.WasteStream;
import com.example.demo.respository.RegisteredStreamPickupRepository;
import com.example.demo.respository.ServiceProviderRepository;
import com.example.demo.respository.WasteStreamRepository;

import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
class RegisteredStreamPickupServiceTest {

	@Mock
	private RegisteredStreamPickupRepository pickupRepository;

	@Mock
	private WasteStreamRepository wasteStreamRepository;

	@Mock
	private ServiceProviderRepository providerRepository;

	@InjectMocks
	private RegisteredStreamPickupService pickupService;

	private int wasteStreamId = 0;

	private int serviceProviderId = 0;

	private LocalDate pickupDate = LocalDate.now();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		wasteStreamId = 1;
		serviceProviderId = 2;

	}

	@Test
	void testRegisterStreamPickup() {

		WasteStream wasteStream = new WasteStream();
		wasteStream.setId(wasteStreamId);

		ServiceProvider serviceProvider = new ServiceProvider();
		serviceProvider.setId(serviceProviderId);

		when(wasteStreamRepository.findById(wasteStreamId)).thenReturn(java.util.Optional.of(wasteStream));
		when(providerRepository.findById(serviceProviderId)).thenReturn(java.util.Optional.of(serviceProvider));

		// Call the service method
		pickupService.registerStreamPickup(wasteStreamId, serviceProviderId, pickupDate);

		// Verify that the save method was called with the correct arguments
		verify(pickupRepository).save(any(RegisterStreamPickup.class));
	}

	@Test
	void testRegisterStreamPickupWasteStreamNotFound() {

		when(wasteStreamRepository.findById(wasteStreamId)).thenReturn(java.util.Optional.empty());

		// Verify that an EntityNotFoundException is thrown
		assertThrows(EntityNotFoundException.class,
				() -> pickupService.registerStreamPickup(wasteStreamId, serviceProviderId, pickupDate));

		// Ensure that the save method is not called
		verify(pickupRepository, Mockito.never()).save(any(RegisterStreamPickup.class));
	}

	@Test
	void testRegisterStreamPickupServiceProviderNotFound() {

		WasteStream wasteStream = new WasteStream();
		wasteStream.setId(wasteStreamId);

		when(wasteStreamRepository.findById(wasteStreamId)).thenReturn(java.util.Optional.of(wasteStream));
		when(providerRepository.findById(serviceProviderId)).thenReturn(java.util.Optional.empty());

		// Verify that an EntityNotFoundException is thrown
		assertThrows(EntityNotFoundException.class,
				() -> pickupService.registerStreamPickup(wasteStreamId, serviceProviderId, pickupDate));

		// Ensure that the save method is not called
		verify(pickupRepository, Mockito.never()).save(any(RegisterStreamPickup.class));
	}
}
