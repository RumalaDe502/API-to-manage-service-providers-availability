package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AvailableServiceProviderResp;
import com.example.demo.entities.ServiceProvider;
import com.example.demo.entities.ServiceProviderCoverage;
import com.example.demo.entities.WasteStream;
import com.example.demo.respository.ServiceProviderCoverageRepository;
import com.example.demo.respository.ServiceProviderRepository;
import com.example.demo.respository.WasteStreamRepository;
import com.example.demo.utilities.SeenonsUtility;

@Service
public class ServiceProviderService {

	private final ServiceProviderCoverageRepository coverageRepository;

	@Autowired
	WasteStreamRepository wasteStreamRepository;

	private final ServiceProviderRepository providerRepository;

	public ServiceProviderService(ServiceProviderCoverageRepository coverageRepository,
			ServiceProviderRepository providerRepository) {
		this.coverageRepository = coverageRepository;
		this.providerRepository = providerRepository;
	}

	public List<AvailableServiceProviderResp> getAvailableServiceProviders(int postalCode, LocalDate date) {
		List<Object[]> result = new ArrayList<>();
		String dayOfWeek = SeenonsUtility.convertUppercaseToMixedCase(date.getDayOfWeek().toString());
		System.out.println("Day Of the week" + dayOfWeek);
		// Find available service provider based on postal code
		result = coverageRepository.findCoveragesByPostalCode(postalCode, dayOfWeek);

		System.out.println(result.size());
		List<ServiceProviderCoverage> coverages = new ArrayList<>();
		for (Object[] row : result) {
			ServiceProviderCoverage coverage = getServiceProviderCoverage(row);
			coverages.add(coverage);
		}
		return processAvailableCoverages(coverages, postalCode, date);
	}

	private static ServiceProviderCoverage getServiceProviderCoverage(Object[] row) {
		ServiceProviderCoverage coverage = new ServiceProviderCoverage();
		ServiceProvider provider = new ServiceProvider();
		coverage.setId((Integer) row[0]);
		provider.setId((Integer) row[1]);
		provider.setName((String) row[2]);
		coverage.setServiceProvider(provider);
		return coverage;
	}

	private List<AvailableServiceProviderResp> processAvailableCoverages(List<ServiceProviderCoverage> coverages,
			int postalCode, LocalDate date) {

		// Process the list of coverages to get the result in the desired format
		List<String> result = new ArrayList<>();
		WasteStream wasteStream = new WasteStream();
		for (ServiceProviderCoverage coverage : coverages) {
			wasteStream = wasteStreamRepository.findByServiceProviderCovId(coverage.getId());
			String wasteStreamName = wasteStream.getLabel();
			String serviceProviderName = coverage.getServiceProvider().getName();
			result.add(serviceProviderName + "(" + wasteStreamName + ")");
		}
		List<String> combinedList = combineStrings(result);
		return getFormattedResult(combinedList, postalCode, date);
	}

	private List<String> combineStrings(List<String> result) {
		Map<String, List<String>> groupedByProvider = result.stream()
				.collect(Collectors.groupingBy(s -> s.substring(0, s.indexOf("("))));

		// Combine strings for each service provider
		List<String> combinedList = new ArrayList<>();
		groupedByProvider.forEach((provider, wasteStreams) -> {
			String combinedString = provider
					+ "(" + String.join(", ", wasteStreams.stream()
							.map(s -> s.substring(s.indexOf("(") + 1, s.indexOf(")"))).collect(Collectors.toList()))
					+ ")";
			combinedList.add(combinedString);
		});
		return combinedList;
	}

	// Gives the formatted Result.
	private static List<AvailableServiceProviderResp> getFormattedResult(List<String> result, int postalCode,
			LocalDate date) {
		List<AvailableServiceProviderResp> providerResps = new ArrayList<>();
		AvailableServiceProviderResp providerResp = new AvailableServiceProviderResp();
		providerResp.setPostalCode(postalCode);
		providerResp.setDate(SeenonsUtility.getFomattedDateInString(date));
		providerResp.setResult(String.join(", ", result));
		providerResps.add(providerResp);
		return providerResps;
	}

}