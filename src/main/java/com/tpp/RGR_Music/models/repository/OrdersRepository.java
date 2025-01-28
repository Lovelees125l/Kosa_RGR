package com.tpp.RGR_Music.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpp.RGR_Music.models.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
}
