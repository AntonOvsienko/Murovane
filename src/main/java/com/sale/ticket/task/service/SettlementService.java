package com.sale.ticket.task.service;

import com.sale.ticket.task.model.Individual;
import com.sale.ticket.task.model.Settlement;

import java.util.List;

public interface SettlementService {

    Settlement createSettler(Settlement settlement);

    Settlement updateSettler(Settlement settlement);

    Settlement getSettlementById(Integer id);

    List<Settlement> getSettlementList();

}
