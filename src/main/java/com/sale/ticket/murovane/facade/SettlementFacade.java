package com.sale.ticket.murovane.facade;

import com.sale.ticket.murovane.model.Settlement;

import java.util.List;

public interface SettlementFacade {

    Settlement createSettlement(Integer quantity, String name);

    Settlement getSettlerById(Integer id);

    List<Settlement> getSettlementList();

    void deleteSettlement(Integer settlement);

    Settlement updateSettlerById(Integer id, List<String> messages);
}
