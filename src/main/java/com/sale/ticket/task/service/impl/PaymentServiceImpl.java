package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Billet;
import com.sale.ticket.task.model.Payment;
import com.sale.ticket.task.model.PaymentStatus;
import com.sale.ticket.task.repository.PaymentRepository;
import com.sale.ticket.task.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public void createNewPayment(Billet billet) {
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setStatus("NEW");
        Payment payment = new Payment();
        payment.setBillet(billet);
        payment.setStatus(paymentStatus);
        paymentRepository.saveAndFlush(payment);
    }
}
