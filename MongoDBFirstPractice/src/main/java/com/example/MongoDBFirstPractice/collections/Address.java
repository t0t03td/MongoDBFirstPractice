package com.example.MongoDBFirstPractice.collections;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	private String address1;
    private String address2;
    private String city;
}