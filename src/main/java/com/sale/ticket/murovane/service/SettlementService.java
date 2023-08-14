package com.sale.ticket.murovane.service;

import com.sale.ticket.murovane.model.Settlement;

import java.time.LocalDate;
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
