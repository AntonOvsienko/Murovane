package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Individual;
import com.sale.ticket.task.model.Settlement;
import com.sale.ticket.task.repository.SettlementRepository;
import com.sale.ticket.task.service.SettlementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        return settlementRepository.saveAndFlush(settlement);
    }

    @Override
    public Settlement getSettlementById(Integer id) {
        return settlementRepository.getReferenceById(id);
    }

    @Override
    public List<Settlement> getSettlementList() {
        if (settlementRepository.findAll().size() > 0) {
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
}
