package com.sale.ticket.Murovane.service.impl;

import com.sale.ticket.Murovane.model.Settlement;
import com.sale.ticket.Murovane.repository.SettlementRepository;
import com.sale.ticket.Murovane.service.SettlementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public Settlement updateSettler(Settlement settlement) {
        settlementRepository.deleteById(settlement.getId());
        return settlementRepository.save(settlement);
    }

    @Override
    public Settlement getSettlementById(Integer id) {
        return settlementRepository.getReferenceById(id);
    }

    @Override
    public List<Settlement> getSettlementList() {
        if (!settlementRepository.findAll().isEmpty()) {
            return settlementRepository.findAll();
        }
        return null;
    }

    @Override
    public void deleteSettlement(Integer settlement) {
        if (settlementRepository.existsById(settlement)) {
            settlementRepository.deleteById(settlement);
        }
    }

    @Override
    public LocalDate bornDate(int age, LocalDate settlement) {
        return settlement.minusYears(age);
    }

    @Override
    public void createLogHistory(Settlement settlement) {

    }
}
