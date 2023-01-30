package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.ManName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ManNameRepository extends JpaRepository<ManName,Integer>, CrudRepository<ManName, Integer> {

}
