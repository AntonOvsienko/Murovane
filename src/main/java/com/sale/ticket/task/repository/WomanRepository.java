package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Woman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WomanRepository extends JpaRepository<Woman,Integer>, CrudRepository<Woman, Integer> {

}
