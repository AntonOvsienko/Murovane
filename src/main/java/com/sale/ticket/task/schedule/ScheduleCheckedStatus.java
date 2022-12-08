package com.sale.ticket.task.schedule;

import com.sale.ticket.task.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScheduleCheckedStatus {

    private final PaymentService paymentService;

    @Scheduled (cron = "0 07 20 * * ?")
    public void cronJob(){
        paymentService.deleteFailedPayment();
    }

}
