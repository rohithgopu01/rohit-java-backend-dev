package com.amazon.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.backend.Entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
