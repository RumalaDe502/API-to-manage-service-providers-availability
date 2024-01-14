package com.example.demo.dto;

public class StreamPickupRequest {
	private Integer wasteStreamId;
	private Integer serviceProviderId;
	private String pickupDate;

	public Integer getWasteStreamId() {
		return wasteStreamId;
	}

	public void setWasteStreamId(Integer wasteStreamId) {
		this.wasteStreamId = wasteStreamId;
	}

	public Integer getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(Integer serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public String getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(String pickupDate) {
		this.pickupDate = pickupDate;
	}

}
