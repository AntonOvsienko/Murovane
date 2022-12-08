package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Billet;
import com.sale.ticket.task.model.Payment;
import com.sale.ticket.task.model.PaymentStatus;
import com.sale.ticket.task.model.Route;
import com.sale.ticket.task.repository.BilletRepository;
import com.sale.ticket.task.repository.PaymentRepository;
import com.sale.ticket.task.repository.PaymentStatusRepository;
import com.sale.ticket.task.repository.RouteRepository;
import com.sale.ticket.task.service.PaymentService;
import com.sale.ticket.task.service.PaymentStatusService;
import com.sale.ticket.task.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentStatusService paymentStatusService;
    private final RouteService routeService;
    private final RouteRepository routeRepository;
    private final BilletRepository billetRepository;

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

    @Transactional
    @Override
    public void deleteFailedPayment() {
        List<Payment> payments = paymentRepository.getAllPaymentHasFailedStatus();
        System.out.println(payments);
        for (Payment payment : payments) {
            Route route = routeService.getRouteByIdPayment(payment.getId());
            Billet billet = billetRepository.getReferenceById(payment.getBillet().getId());
            route.setCount(route.getCount() + 1);
            routeRepository.save(route);
            paymentRepository.delete(payment);
            billet.setRoute(null);
            billetRepository.save(billet);
            billetRepository.delete(billet);
        }
    }

    private void changeStatus(Payment payment) {
        Random random = new Random();
        List<PaymentStatus> paymentStatuses = paymentStatusService.getAllStatus();
        paymentStatuses.sort((o1, o2) -> {
            if (o1.getId() < o2.getId()) {
                return -1;
            }
            if (o1.getId() > o2.getId()) {
                return 1;
            }
            return 0;
        });
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
