package com.sale.ticket.task.service;

import com.sale.ticket.task.model.Individual;
import com.sale.ticket.task.model.Settlement;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface SettlementService {

    Settlement createSettler(Settlement settlement);

    Settlement updateSettler(Settlement settlement);

    Settlement getSettlementById(Integer id);

    List<Settlement> getSettlementList();

    void deleteSettlement(Integer settlement);

    LocalDate bornDate(int age, LocalDate settlement);

    void createLogHistory(Settlement settlement);
}
