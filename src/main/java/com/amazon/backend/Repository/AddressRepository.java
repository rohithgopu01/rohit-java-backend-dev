package com.amazon.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.backend.Entity.Address;
import java.util.List;
import java.util.Optional;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
   
	List<Address> findByUserId(int userId);
	
	Optional<Address> findByUserIdAndAddressId(int userId,int addressId);
}
