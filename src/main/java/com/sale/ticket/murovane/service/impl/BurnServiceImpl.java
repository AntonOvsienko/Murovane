package com.sale.ticket.murovane.service.impl;

import com.sale.ticket.murovane.model.Man;
import com.sale.ticket.murovane.model.ManName;
import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.model.Surname;
import com.sale.ticket.murovane.model.Woman;
import com.sale.ticket.murovane.model.WomanName;
import com.sale.ticket.murovane.service.BurnService;
import com.sale.ticket.murovane.service.ManNameService;
import com.sale.ticket.murovane.service.ManService;
import com.sale.ticket.murovane.service.SettlementService;
import com.sale.ticket.murovane.service.SurnameService;
import com.sale.ticket.murovane.service.WomanNameService;
import com.sale.ticket.murovane.service.WomanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BurnServiceImpl implements BurnService {

    @Resource
    private ManService manService;
    @Resource
    private ManNameService manNameService;
    @Resource
    private WomanService womanService;
    @Resource
    private WomanNameService womanNameService;
    @Resource
    private SurnameService surnameService;
    @Resource
    private SettlementService settlementService;

    @Override
    public Integer married(Settlement settlement, Integer countMarried) {
        List<Man> listMan = manService.getManUnderMarried(settlement);
        for (int i = 0; i < listMan.size(); i++) {
            List<Woman> listWoman = womanService.getWomenUnderMarried(settlement);
            if (listWoman.isEmpty()) {
                break;
            }
            int random = (int) (Math.random() * 5);
            if (random == 0) {
                Man currentMan = listMan.get(i);
                Woman currentWoman = getWife(listWoman);
                currentMan.setWife(currentWoman);
                currentWoman.setHusband(currentMan);

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
        for (int i = 0; i < listWoman.size(); i++) {
            int random = (int) (Math.random() * (10 + 5 * listWoman.get(i).getCountBaby()));
            if (random == 0) {
                countPregnant++;
                Woman currentWoman = listWoman.get(i);
                currentWoman.setPregnant(true);
            }
        }
        return countPregnant;
    }

    @Override
    public Integer childbirth(Settlement settlement, Integer countBaby) {
        List<Woman> listWoman = womanService.getWomanPregnant(settlement);
        for (int i = 0; i < listWoman.size(); i++) {
            int gestation = listWoman.get(i).getPregnantDuration();
            gestation++;
            if (gestation == 9) {
                countBaby++;
                Woman currentWoman = listWoman.get(i);
                currentWoman.setPregnant(false);
                currentWoman.setPregnantDuration(0);
                currentWoman.setPregnantRecess(12);
                currentWoman.setCountBaby(currentWoman.getCountBaby() + 1);
                this.addNewIndividual(settlement, 0);
            } else {
                Woman currentWoman = listWoman.get(i);
                currentWoman.setPregnantDuration(gestation);
            }
        }
        return countBaby;
    }

    @Override
    public void addNewIndividual(Settlement settlement, Integer maxAge) {
        List<ManName> manNameList = manNameService.getListManName();
        List<WomanName> womanNameList = womanNameService.getListWomanName();
        List<Surname> surnameList = surnameService.getListSurname();
        final LocalDate timeNow = settlement.getSettlementTime();
        int sex = (int) (Math.random() * 2);
        int age = (int) (Math.random() * maxAge);
        int manListCount = (int) (Math.random() * manNameList.size());
        int womanListCount = (int) (Math.random() * womanNameList.size());
        int surnameListCount = (int) (Math.random() * surnameList.size());
        if (sex == 1) {
            Man man = new Man();
            man.setName(manNameList.get(manListCount));
            man.setSurname(surnameList.get(surnameListCount));
            man.setHealth(5);
            man.setDateBorn(settlementService.bornDate(age, timeNow));
            man.setSettlement(settlement);
            settlement.getMen().add(man);
        } else {
            Woman woman = new Woman();
            woman.setName(womanNameList.get(womanListCount));
            woman.setSurname(surnameList.get(surnameListCount));
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
        for (int i = 0; i < womanList.size(); i++) {
            Woman woman = womanList.get(i);
            woman.setPregnantRecess(woman.getPregnantRecess() - 1);
        }
    }
}
