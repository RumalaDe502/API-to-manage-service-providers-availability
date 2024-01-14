package com.example.demo.service;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.RegisterStreamPickup;
import com.example.demo.entities.ServiceProvider;
import com.example.demo.entities.WasteStream;
import com.example.demo.respository.RegisteredStreamPickupRepository;
import com.example.demo.respository.ServiceProviderRepository;
import com.example.demo.respository.WasteStreamRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RegisteredStreamPickupService {

	@Autowired
	private RegisteredStreamPickupRepository pickupRepository;

	@Autowired
	private WasteStreamRepository wasteStreamRepository;

	@Autowired
	private ServiceProviderRepository providerRepository;

	public void registerStreamPickup(Integer wasteStreamId, Integer serviceProviderId, LocalDate pickupDate) {
		WasteStream wasteStream = wasteStreamRepository.findById(wasteStreamId)
				.orElseThrow(() -> new EntityNotFoundException("WasteStream not found with id: " + wasteStreamId));

		ServiceProvider serviceProvider = providerRepository.findById(serviceProviderId).orElseThrow(
				() -> new EntityNotFoundException("ServiceProvider not found with id: " + serviceProviderId));

		RegisterStreamPickup registeredStreamPickup = new RegisterStreamPickup();
		Random random = new Random();
		registeredStreamPickup.setId(random.nextInt());
		registeredStreamPickup.setWasteStream(wasteStream);
		registeredStreamPickup.setServiceProvider(serviceProvider);
		registeredStreamPickup.setPickupDate(pickupDate);

		pickupRepository.save(registeredStreamPickup);
	}
}
