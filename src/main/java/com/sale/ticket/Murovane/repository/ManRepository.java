package com.sale.ticket.Murovane.repository;

import com.sale.ticket.Murovane.model.Man;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ManRepository extends JpaRepository<Man,Integer>, CrudRepository<Man, Integer> {

    @Query (value = "SELECT m FROM Man m" +
            " WHERE m.settlement.id = :id AND m.wife IS NULL")
    List<Man> getManUnderMarried(@Param ("id") Integer id);
}
