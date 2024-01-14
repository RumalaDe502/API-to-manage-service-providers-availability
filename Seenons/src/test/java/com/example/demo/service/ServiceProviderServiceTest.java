package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.AvailableServiceProviderResp;
import com.example.demo.entities.WasteStream;
import com.example.demo.respository.ServiceProviderCoverageRepository;
import com.example.demo.respository.ServiceProviderRepository;
import com.example.demo.respository.WasteStreamRepository;
import com.example.demo.utilities.SeenonsUtility;

@SpringBootTest
public class ServiceProviderServiceTest {

	@InjectMocks
	private ServiceProviderService serviceProviderService;

	@Mock
	private ServiceProviderCoverageRepository coverageRepository;

	@Mock
	private WasteStreamRepository wasteStreamRepository;

	@Mock
	private ServiceProviderRepository providerRepository;

	@BeforeEach
	void setUp() {
		coverageRepository = mock(ServiceProviderCoverageRepository.class);
		wasteStreamRepository = mock(WasteStreamRepository.class);
		providerRepository = mock(ServiceProviderRepository.class);

		serviceProviderService = new ServiceProviderService(coverageRepository, providerRepository);
		serviceProviderService.wasteStreamRepository = wasteStreamRepository;
	}

	@Test
	public void testGetAvailableServiceProviders() {
		int postalCode = 12345;
		LocalDate date = LocalDate.of(2022, 1, 1);
		// Mocked result from the repository
		List<Object[]> mockedResult = Arrays.asList(new Object[] { 1, 1, "ServiceName", 12345, "Monday" },
				new Object[] { 2, 2, "AnotherService", 12345, "Monday" });

		// Mocking the behavior of the repository
		when(coverageRepository.findCoveragesByPostalCode(postalCode, "Monday")).thenReturn(mockedResult);

		// Mocking the behavior of wasteStreamRepository
		when(wasteStreamRepository.findByServiceProviderCovId(1)).thenReturn(createWasteStream(1, "WasteStream1"));
		when(wasteStreamRepository.findByServiceProviderCovId(2)).thenReturn(createWasteStream(2, "WasteStream2"));

		// Actual service call
		List<AvailableServiceProviderResp> result = serviceProviderService.getAvailableServiceProviders(postalCode,
				date);

		// Expected result
		List<AvailableServiceProviderResp> expectedResult = new ArrayList<>();
		AvailableServiceProviderResp providerResp = new AvailableServiceProviderResp();
		providerResp.setPostalCode(postalCode);
		providerResp.setDate(SeenonsUtility.getFomattedDateInString(date));
		providerResp.setResult("ServiceName(WasteStream1), AnotherService(WasteStream2)");
		expectedResult.add(providerResp);

		// Assertions
		assertEquals(1, result.size());
	}

	@Test
	public void testGetAvailableServiceProvidersNoCoverage() throws Exception {
		LocalDate date = LocalDate.of(2023, 10, 5);
		when(coverageRepository.findCoveragesByPostalCode(2000, "Thursday")).thenReturn(new ArrayList<>());
		List<AvailableServiceProviderResp> result = serviceProviderService.getAvailableServiceProviders(2000, date);
		assertNotEquals(0, result.size());
	}

	private WasteStream createWasteStream(int id, String label) {
		WasteStream wasteStream = new WasteStream();
		wasteStream.setId(id);
		wasteStream.setLabel(label);
		return wasteStream;
	}
}
