package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AvailableServiceProviderResp {

	@JsonProperty("postal_code")
	private Integer postalCode;

	@JsonProperty("date")
	private String date;

	@JsonProperty("result")
	private String result;

	public Integer getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
