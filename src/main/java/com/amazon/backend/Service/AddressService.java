package com.amazon.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazon.backend.Entity.Address;
import com.amazon.backend.Exceptions.AddressNotFoundException;
import com.amazon.backend.Repository.AddressRepository;
import com.amazon.backend.constants.AddressConstants;
import com.amazon.backend.pojo.AddressAddApiData;

@Service
public class AddressService {

	@Autowired
	private  AddressRepository addressRepository;
	
	
	public Address addAddress(AddressAddApiData ApiData) {
		
		Address address=new Address();
		address.setUserId(ApiData.getUserId());
		address.setAddressLine1(ApiData.getAddressLine1());
		address.setAddressLine2(ApiData.getAddressLine2());
		address.setCity(ApiData.getCity());
		address.setState(ApiData.getState());
		address.setPinCode(ApiData.getPinCode());
		address.setCountry(ApiData.getCountry());
		address.setLatitude(ApiData.getLatitude());
		address.setLongitude(ApiData.getLongitude());
		address.setDefault(ApiData.isDefault());
		address.setAddressType(ApiData.getAddressType());
		
		return   addressRepository.save(address);
		
	}
	public  List<Address> getAddress(int userId) {
	    List<Address> addresses=addressRepository.findByUserId(userId);
	    return addresses;
	}
	@Transactional
	public void deleteAddress(int userId,int addressId) {
		Optional<Address> dbOptional=addressRepository.findByUserIdAndAddressId(userId, addressId);
		if (dbOptional.isEmpty()) {
			throw new AddressNotFoundException(AddressConstants.EXCEPTION_ADDRESS_NOT_FOUND,userId,addressId);
		}
		addressRepository.deleteById(addressId);
	}
}