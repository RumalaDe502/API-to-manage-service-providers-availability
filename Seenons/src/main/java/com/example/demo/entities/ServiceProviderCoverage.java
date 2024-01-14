package com.example.demo.entities;

import java.util.List;

import com.example.demo.constant.Weekday;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "service_provider_coverage")
public class ServiceProviderCoverage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "service_provider_id", nullable = false)
	private ServiceProvider serviceProvider;

	@Column(name = "postal_code_start", nullable = false)
	private Integer postalCodeStart;

	@Column(name = "postal_code_end", nullable = false)
	private Integer postalCodeEnd;

	@ElementCollection(targetClass = Weekday.class)
	@CollectionTable(name = "week_days", joinColumns = @JoinColumn(name = "service_provider_cov_id"))
	@Enumerated(EnumType.STRING)
	@Column(name = "weekday")
	private List<Weekday> weekdayAvailability;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public Integer getPostalCodeStart() {
		return postalCodeStart;
	}

	public void setPostalCodeStart(Integer postalCodeStart) {
		this.postalCodeStart = postalCodeStart;
	}

	public Integer getPostalCodeEnd() {
		return postalCodeEnd;
	}

	public void setPostalCodeEnd(Integer postalCodeEnd) {
		this.postalCodeEnd = postalCodeEnd;
	}

}