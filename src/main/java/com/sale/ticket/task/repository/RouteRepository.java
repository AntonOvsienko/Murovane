package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RouteRepository extends JpaRepository<Route,Integer>, CrudRepository<Route, Integer> {
}
