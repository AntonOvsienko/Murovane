package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Billet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilletRepository extends JpaRepository<Billet,Integer> {
}
