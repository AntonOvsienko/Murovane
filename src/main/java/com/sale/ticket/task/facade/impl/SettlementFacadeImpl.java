package com.sale.ticket.task.facade.impl;

import com.sale.ticket.task.facade.SettlementFacade;
import com.sale.ticket.task.model.Man;
import com.sale.ticket.task.model.ManName;
import com.sale.ticket.task.model.Settlement;
import com.sale.ticket.task.model.Surname;
import com.sale.ticket.task.model.Woman;
import com.sale.ticket.task.model.WomanName;
import com.sale.ticket.task.repository.SettlementRepository;
import com.sale.ticket.task.service.ManNameService;
import com.sale.ticket.task.service.ManService;
import com.sale.ticket.task.service.SettlementService;
import com.sale.ticket.task.service.SurnameService;
import com.sale.ticket.task.service.WomanNameService;
import com.sale.ticket.task.service.WomanService;
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
    private SettlementRepository settlementRepository;
    @Resource
    private ManNameService manNameService;
    @Resource
    private ManService manService;
    @Resource
    private WomanService womanService;
    @Resource
    private WomanNameService womanNameService;
    @Resource
    private SurnameService surnameService;

    @Transactional
    @Override
    public Settlement createSettlement(Integer quantity, String name) {
        Settlement settlement = settlementRepository.save(new Settlement());
        List<ManName> manNameList = manNameService.getListManName();
        List<WomanName> womanNameList = womanNameService.getListWomanName();
        List<Surname> surnameList = surnameService.getListSurname();
        settlement.setLastTime(LocalDateTime.now());
        settlement.setSettlementTime(LocalDate.of(100,1,1));
        for (int i = 0; i < quantity; i++) {
            addNewIndividual(settlement, manNameList, womanNameList, surnameList);
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

    @Transactional
    @Override
    public Settlement updateSettlerById(Integer id) {
        Settlement settlement = settlementService.getSettlementById(id);
        Integer countMonth = getCountMonth(settlement);
        for (int i=0;i < countMonth;i++){

        }
        updateDate(settlement, countMonth);
        settlementService.updateSettler(settlement);
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

    private void addNewIndividual(Settlement settlement, List<ManName> manNameList, List<WomanName> womanNameList, List<Surname> surnameList) {
        int sex = (int) (Math.random() * 2);
        int age = (int) (Math.random() * 45) + 2;
        int manListCount = (int) (Math.random() * manNameList.size());
        int womanListCount = (int) (Math.random() * womanNameList.size());
        int surnameListCount = (int) (Math.random() * surnameList.size());
        if (sex == 1) {
            Man man = new Man();
            man.setName(manNameList.get(manListCount));
            man.setSurname(surnameList.get(surnameListCount));
            man.setHealth(5);
            man.setDateBorn(settlementService.bornDate(age,settlement.getSettlementTime()));
            man.setSettlement(settlement);
            man.setIsLife(Boolean.TRUE);
            manService.addMan(man);
            settlement.getMen().add(man);
        } else {
            Woman woman = new Woman();
            woman.setName(womanNameList.get(womanListCount));
            woman.setSurname(surnameList.get(surnameListCount));
            woman.setHealth(5);
            woman.setDateBorn(settlementService.bornDate(age,settlement.getSettlementTime()));
            woman.setSettlement(settlement);
            woman.setIsLife(Boolean.TRUE);
            woman.setPregnant(Boolean.FALSE);
            womanService.addWoman(woman);
            settlement.getWomen().add(woman);
        }
    }
}
