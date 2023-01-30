package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Payment;
import com.sale.ticket.task.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface RouteRepository extends JpaRepository<Route,Integer>, CrudRepository<Route, Integer> {

    @Query (value = "SELECT pbr FROM Payment p" +
            " JOIN p.billet pb" +
            " JOIN p.status ps" +
            " JOIN pb.route pbr" +
            " JOIN pbr.output pbro" +
            " JOIN pbr.input pbri" +
            " WHERE p.id = :id")
    Route getRouteByPaymentId(@Param ("id") Integer id);
}
