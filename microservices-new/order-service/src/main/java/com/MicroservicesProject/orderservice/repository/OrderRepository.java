package com.MicroservicesProject.orderservice.repository;

import com.MicroservicesProject.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
