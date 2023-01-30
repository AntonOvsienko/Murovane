package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Individual;
import com.sale.ticket.task.model.Settlement;
import com.sale.ticket.task.repository.SettlementRepository;
import com.sale.ticket.task.service.SettlementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository settlementRepository;

    @Override
    public Settlement createSettler(Settlement settlement) {
        return settlementRepository.save(settlement);
    }

    @Override
    public void updateSettler(Settlement settlement) {
        settlementRepository.saveAndFlush(settlement);
    }


}
