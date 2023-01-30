package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Surname;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface SurnameRepository extends JpaRepository<Surname,Integer>, CrudRepository<Surname, Integer> {

}
