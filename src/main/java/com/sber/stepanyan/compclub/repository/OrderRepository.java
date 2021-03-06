package com.sber.stepanyan.compclub.repository;

import com.sber.stepanyan.compclub.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByOrderNumber(Long orderNumber);

    Optional<Order> findFirstByOrderByOrderNumberDesc();

}
