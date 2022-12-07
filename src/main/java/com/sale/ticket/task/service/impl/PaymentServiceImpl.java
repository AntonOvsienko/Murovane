package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Billet;
import com.sale.ticket.task.model.Payment;
import com.sale.ticket.task.model.PaymentStatus;
import com.sale.ticket.task.repository.BilletRepository;
import com.sale.ticket.task.repository.PaymentRepository;
import com.sale.ticket.task.repository.PaymentStatusRepository;
import com.sale.ticket.task.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStatusRepository paymentStatusRepository;

    @Transactional
    @Override
    public void createNewPayment(Billet billet) {
        PaymentStatus paymentStatus = paymentStatusRepository.findAll()
                .stream().filter(status->status.getStatus().equals("NEW"))
                .findFirst().get();
        paymentStatus.setStatus("NEW");
        Payment payment = new Payment();
        payment.setBillet(billet);
        payment.setStatus(paymentStatus);
        paymentRepository.saveAndFlush(payment);
    }

    @Override
    public Payment getPaymentByIdBillet(Integer id) {
        return paymentRepository.getPaymentByBilletId(id);
    }
}
