package com.sale.ticket.task.service;

import com.sale.ticket.task.model.Billet;
import com.sale.ticket.task.model.Payment;

public interface PaymentService {

    void createNewPayment(Billet billet);

    Payment getPaymentByIdBillet(Integer id);

    Boolean getPaymentByIdBilletAndInitial(Integer id, String name, String surname, String patronomic);

    void deleteFailedPayment();

}
