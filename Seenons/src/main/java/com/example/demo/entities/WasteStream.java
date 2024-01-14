package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "waste_stream")
public class WasteStream {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "label", nullable = false)
	private String label;

	@Column(name = "category", nullable = false)
	private String category;

	@ManyToOne
	@JoinColumn(name = "service_provider_cov_id", nullable = false)
	private ServiceProviderCoverage serviceProviderCoverage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ServiceProviderCoverage getServiceProviderCoverage() {
		return serviceProviderCoverage;
	}

	public void setServiceProviderCoverage(ServiceProviderCoverage serviceProviderCoverage) {
		this.serviceProviderCoverage = serviceProviderCoverage;
	}

}
