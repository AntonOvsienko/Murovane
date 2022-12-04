package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository <City,Integer> {
}
