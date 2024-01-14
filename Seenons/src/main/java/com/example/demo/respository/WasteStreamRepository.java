package com.example.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.WasteStream;

@Repository
public interface WasteStreamRepository extends JpaRepository<WasteStream, Integer> {
	@Query(value = "select * from waste_stream ws  "
			+ "where ws.service_provider_cov_id = :serviceProviderCovId", nativeQuery = true)
	@Transactional
	WasteStream findByServiceProviderCovId(Integer serviceProviderCovId);

}
