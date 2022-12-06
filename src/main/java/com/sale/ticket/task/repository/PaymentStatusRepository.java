package com.sale.ticket.task.repository;

import com.sale.ticket.task.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus,Integer> {

}
