package com.amazon.backend.pojo;

import java.time.LocalDateTime;

import com.amazon.backend.enums.AddressType;

import lombok.Data;

@Data
public class AddressAddApiData {

	private int userId;
	private String addressLine1; private String addressLine2; private String city; private String state;
	private String pinCode; private String country;
	private Double latitude; private Double longitude;
	private boolean isDefault;
	private AddressType addressType;
    private LocalDateTime createdOn = LocalDateTime.now();
	private LocalDateTime updatedOn = LocalDateTime.now();
}
 

