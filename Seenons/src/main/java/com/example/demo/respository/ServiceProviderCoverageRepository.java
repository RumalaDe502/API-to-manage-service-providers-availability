package com.example.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.ServiceProviderCoverage;

@Repository
public interface ServiceProviderCoverageRepository extends JpaRepository<ServiceProviderCoverage, Integer> {

	@Query(value = "Select s.id, s.service_provider_id,p.name,w.label from service_provider_coverage s inner join service_provider p on p.id = s.service_provider_id \r\n"
			+ "inner join waste_stream w on w.service_provider_cov_id = s.id \r\n"
			+ "inner join week_days wd on wd.service_provider_cov_id = s.id \r\n"
			+ "where :postalCode between s.postal_code_start and s.postal_code_end and wd.weekday = :dayOfWeek", nativeQuery = true)
	@Transactional
	List<Object[]> findCoveragesByPostalCode(int postalCode, String dayOfWeek);

}