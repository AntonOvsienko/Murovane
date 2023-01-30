package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Man;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ManRepository extends JpaRepository<Man,Integer>, CrudRepository<Man, Integer> {

}
