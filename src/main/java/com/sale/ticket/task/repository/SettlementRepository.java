package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SettlementRepository extends JpaRepository<Settlement,Integer>, CrudRepository<Settlement, Integer> {

}
