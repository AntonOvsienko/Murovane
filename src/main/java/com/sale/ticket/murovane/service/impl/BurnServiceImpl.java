package com.sale.ticket.murovane.service.impl;

import com.sale.ticket.murovane.converters.SettlementConverter;
import com.sale.ticket.murovane.generator.RandomStringGenerator;
import com.sale.ticket.murovane.model.Man;
import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.model.Woman;
import com.sale.ticket.murovane.service.BurnService;
import com.sale.ticket.murovane.service.ManService;
import com.sale.ticket.murovane.service.SettlementService;
import com.sale.ticket.murovane.service.WomanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class BurnServiceImpl implements BurnService {

    @Resource
    private ManService manService;
    @Resource
    private WomanService womanService;
    @Resource
    private SettlementService settlementService;
    @Resource
    private SettlementConverter settlementConverter;
    @Resource
    private RandomStringGenerator randomStringGenerator;

    @Override
    public Integer married(Settlement settlement, Integer countMarried) {
        List<Man> listMan = manService.getManUnderMarried(settlement);
        for (Man man : listMan) {
            List<Woman> listWoman = womanService.getWomenUnderMarried(settlement);
            if (listWoman.isEmpty()) {
                break;
            }
            int random = (int) (Math.random() * 10);
            if (random == 0) {
                Woman currentWoman = getWife(listWoman);
                man.setWife(currentWoman);
                currentWoman.setHusband(man);

                countMarried++;
            }
        }
        return countMarried;
    }

    private Woman getWife(List<Woman> listWoman) {
        int count = listWoman.size();
        int random = (int) (Math.random() * (count));
        Woman woman = listWoman.get(random);
        listWoman.remove(woman);
        return woman;
    }

    @Override
    public Integer pregnant(Settlement settlement, Integer countPregnant) {
        List<Woman> listWoman = womanService.getWomanMarriedNotPregnant(settlement);
        for (Woman woman : listWoman) {
            int random = (int) (Math.random() * (5 + 20 * woman.getCountBaby()));
            if (random == 0) {
                countPregnant++;
                woman.setPregnant(true);
            }
        }
        return countPregnant;
    }

    @Override
    public Integer childbirth(Settlement settlement, Integer countBaby) {
        List<Woman> listWoman = womanService.getWomanPregnant(settlement);
        for (Woman woman : listWoman) {
            int gestation = woman.getPregnantDuration();
            gestation++;
            if (gestation == 9) {
                countBaby++;
                woman.setPregnant(false);
                woman.setPregnantDuration(0);
                woman.setPregnantRecess(settlementConverter
                        .getAge(woman.getDateBorn(), settlement.getSettlementTime()) / 2);
                woman.setCountBaby(woman.getCountBaby() + 1);
                this.addNewIndividual(settlement, 0);
            } else {
                woman.setPregnantDuration(gestation);
            }
        }
        return countBaby;
    }

    @Override
    public void addNewIndividual(Settlement settlement, Integer maxAge) {
        final LocalDate timeNow = settlement.getSettlementTime();
        int sex = (int) (Math.random() * 2);
        int age = (int) (Math.random() * maxAge);
        if (sex == 1) {
            Man man = new Man();
            man.setFullName(randomStringGenerator.generateRandomString());
            man.setHealth(5);
            man.setDateBorn(settlementService.bornDate(age, timeNow));
            man.setSettlement(settlement);
            settlement.getMen().add(man);
        } else {
            Woman woman = new Woman();
            woman.setFullName(randomStringGenerator.generateRandomString());
            woman.setHealth(5);
            woman.setDateBorn(settlementService.bornDate(age, settlement.getSettlementTime()));
            woman.setSettlement(settlement);
            woman.setPregnantDuration(0);
            woman.setPregnantRecess(0);
            woman.setCountBaby(0);
            woman.setPregnant(Boolean.FALSE);
            settlement.getWomen().add(woman);
        }
    }

    @Override
    public void pregnantRecess(Settlement settlement) {
        List<Woman> womanList = womanService.getWomanOnPregnantRecess(settlement);
        for (Woman woman : womanList) {
            woman.setPregnantRecess(woman.getPregnantRecess() - 1);
        }
    }
}
