package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Billet;
import com.sale.ticket.task.model.Payment;
import com.sale.ticket.task.model.PaymentStatus;
import com.sale.ticket.task.repository.BilletRepository;
import com.sale.ticket.task.repository.PaymentRepository;
import com.sale.ticket.task.repository.PaymentStatusRepository;
import com.sale.ticket.task.service.PaymentService;
import com.sale.ticket.task.service.PaymentStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStatusService paymentStatusService;

    @Transactional
    @Override
    public void createNewPayment(Billet billet) {
        PaymentStatus paymentStatus = paymentStatusService.getAllStatus().stream().filter(status -> status.getStatus().equals("NEW")).findFirst().get();
        Payment payment = new Payment();
        payment.setBillet(billet);
        payment.setStatus(paymentStatus);
        paymentRepository.saveAndFlush(payment);
    }

    @Override
    public Payment getPaymentByIdBillet(Integer id) {
        Payment payment = null;
        try {
            payment = paymentRepository.getPaymentByBilletId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return payment;
    }

    @Override
    @Transactional
    public Boolean getPaymentByIdBilletAndInitial(Integer id, String name, String surname, String patronomic) {
        Payment payment = null;
        payment = paymentRepository.getPaymentByBilletIdAndInitial(id, name, surname, patronomic);
        if (payment != null) {
            changeStatus(payment);
        } else {
            return false;
        }
        return true;
    }

    private void changeStatus(Payment payment) {
        Random random = new Random();
        List<PaymentStatus> paymentStatuses = paymentStatusService.getAllStatus();
        System.out.println(paymentStatuses);
        paymentStatuses.sort((o1, o2) -> {
            if (o1.getId() < o2.getId()) {
                return -1;
            }
            if (o1.getId() > o2.getId()) {
                return 1;
            }
            return 0;
        });
        System.out.println(paymentStatuses);
        int rand = 0;
        while (true) {
            rand = random.nextInt(3);
            if (rand != 0) {
                break;
            }
        }
        if (rand == 1) {
            payment.setStatus(paymentStatuses.get(1));
        } else {
            payment.setStatus(paymentStatuses.get(2));
        }

    }
}
