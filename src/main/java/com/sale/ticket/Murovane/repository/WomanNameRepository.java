package com.sale.ticket.Murovane.repository;

import com.sale.ticket.Murovane.model.WomanName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface WomanNameRepository extends JpaRepository<WomanName,Integer>, CrudRepository<WomanName, Integer> {

}
