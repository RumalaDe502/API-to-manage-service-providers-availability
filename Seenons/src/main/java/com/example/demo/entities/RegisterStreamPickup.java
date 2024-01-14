package com.example.demo.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "register_stream_pickup")
public class RegisterStreamPickup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "waste_stream_id", nullable = false)
	private WasteStream wasteStream;

	@ManyToOne
	@JoinColumn(name = "service_provider_id", nullable = false)
	private ServiceProvider serviceProvider;

	@Column(name = "pickup_date", nullable = false)
	private LocalDate pickupDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public WasteStream getWasteStream() {
		return wasteStream;
	}

	public void setWasteStream(WasteStream wasteStream) {
		this.wasteStream = wasteStream;
	}

	public ServiceProvider getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProvider serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public LocalDate getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(LocalDate pickupDate) {
		this.pickupDate = pickupDate;
	}

}
