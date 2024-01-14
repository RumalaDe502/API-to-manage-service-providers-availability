package com.example.demo.entities;

import com.example.demo.constant.Weekday;

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
@Table(name = "week_days")
public class WeekDays {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "service_provider_cov_id")
	private ServiceProviderCoverage serviceProviderCoverage;

	@Enumerated(EnumType.STRING)
	private Weekday weekday;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ServiceProviderCoverage getServiceProviderCoverage() {
		return serviceProviderCoverage;
	}

	public void setServiceProviderCoverage(ServiceProviderCoverage serviceProviderCoverage) {
		this.serviceProviderCoverage = serviceProviderCoverage;
	}

	public Weekday getWeekday() {
		return weekday;
	}

	public void setWeekday(Weekday weekday) {
		this.weekday = weekday;
	}

}
