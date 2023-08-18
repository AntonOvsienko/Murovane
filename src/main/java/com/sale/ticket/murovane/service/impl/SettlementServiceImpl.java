package com.sale.ticket.murovane.service.impl;

import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.repository.SettlementRepository;
import com.sale.ticket.murovane.service.SettlementService;
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
        int days = (int) (Math.random() * 31);
        return settlement.minusYears(age).minusDays(days);
    }

    @Override
    public void createLogHistory(Settlement settlement) {

    }
}
