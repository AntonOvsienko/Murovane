package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
