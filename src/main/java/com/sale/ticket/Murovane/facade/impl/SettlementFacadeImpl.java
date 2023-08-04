package com.sale.ticket.Murovane.facade.impl;

import com.sale.ticket.Murovane.facade.SettlementFacade;
import com.sale.ticket.Murovane.model.Settlement;
import com.sale.ticket.Murovane.repository.SettlementRepository;
import com.sale.ticket.Murovane.service.BurnService;
import com.sale.ticket.Murovane.service.DeathService;
import com.sale.ticket.Murovane.service.SettlementService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@AllArgsConstructor
public class SettlementFacadeImpl implements SettlementFacade {

    @Resource
    private SettlementService settlementService;
    @Resource
    private BurnService burnService;
    @Resource
    private DeathService deathService;

    @Transactional
    @Override
    public Settlement createSettlement(Integer quantity, String name) {
        Settlement settlement = new Settlement();
        settlement.setLastTime(LocalDateTime.now());
        settlement.setSettlementTime(LocalDate.of(100, 1, 1));
        for (int i = 0; i < quantity; i++) {
            burnService.addNewIndividual(settlement, 45);
        }
        settlement.setName(name);
        return settlementService.updateSettler(settlement);
    }

    @Override
    public Settlement getSettlerById(Integer id) {
        return settlementService.getSettlementById(id);
    }

    @Override
    public List<Settlement> getSettlementList() {
        return settlementService.getSettlementList();
    }

    @Transactional
    @Override
    public void deleteSettlement(Integer settlement) {
        settlementService.deleteSettlement(settlement);
    }

    @Override
    public Settlement updateSettlerById(Integer id, List<String> messages) {
        Settlement settlement = settlementService.getSettlementById(id);
        Integer countMonth = getCountMonth(settlement);
        Integer countMarried = 0;
        Integer countPregnant = 0;
        Integer countBaby = 0;
        Integer countDead = 0;
        for (int i = 0; i < countMonth; i++) {
            updateDate(settlement, 1);
            countMarried = burnService.married(settlement, countMarried);
            countPregnant = burnService.pregnant(settlement, countPregnant);
            countBaby = burnService.childbirth(settlement, countBaby);
            burnService.pregnantRecess(settlement);
            countDead = deathService.dead(settlement, countDead);
//            settlementService.createLogHistory(settlement);
        }
        settlementService.updateSettler(settlement);
        messages.add("Кол-во браков за истекший период - " + countMarried);
        messages.add("Кол-во новых беременностей за истекший период - " + countPregnant);
        messages.add("Кол-во новорожденных за истекший период - " + countBaby);
        messages.add("Кол-во погибних за истекший период - " + countDead);
        return settlement;
    }

    private void updateDate(Settlement settlement, Integer countMonth) {
        LocalDate date = settlement.getSettlementTime();
        settlement.setSettlementTime(date.plusMonths(countMonth));
        settlement.setLastTime(LocalDateTime.now());
    }

    private Integer getCountMonth(Settlement settlement) {
        long seconds = (LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0)) - settlement.getLastTime().toEpochSecond(ZoneOffset.ofHours(0)));
        return (int) (seconds * 12 / 60);
    }
}
