package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.PaymentStatus;
import com.sale.ticket.task.repository.PaymentStatusRepository;
import com.sale.ticket.task.service.PaymentStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentStatusServiceImpl implements PaymentStatusService {

    private final PaymentStatusRepository paymentStatusRepository;

    @Override
    public List<PaymentStatus> getAllStatus() {
        return paymentStatusRepository.findAll();
    }
}
