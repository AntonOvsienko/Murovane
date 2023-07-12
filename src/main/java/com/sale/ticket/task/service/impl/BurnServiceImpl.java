package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Man;
import com.sale.ticket.task.model.ManName;
import com.sale.ticket.task.model.Settlement;
import com.sale.ticket.task.model.Surname;
import com.sale.ticket.task.model.Woman;
import com.sale.ticket.task.model.WomanName;
import com.sale.ticket.task.service.BurnService;
import com.sale.ticket.task.service.ManNameService;
import com.sale.ticket.task.service.ManService;
import com.sale.ticket.task.service.SettlementService;
import com.sale.ticket.task.service.SurnameService;
import com.sale.ticket.task.service.WomanNameService;
import com.sale.ticket.task.service.WomanService;
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
        List<Man> listMan = manService.getManUnderMarried(settlement.getId());
        List<Woman> listWoman = womanService.getWomenUnderMarried(settlement.getId());
        for (int i = 0; i < listMan.size(); i++) {
            if (listWoman.size() == 0) {
                break;
            }
            int random = (int) (Math.random() * 5);
            if (random == 0) {
                Man currentMan = listMan.get(i);
                currentMan.setWife(getWife(listWoman));
                manService.saveMan(currentMan);
                countMarried++;
            }
        }
        return countMarried;
    }

    private Woman getWife(List<Woman> listWoman) {
        int count = listWoman.size();
        double random = Math.random() * (count - 1);
        Woman woman = listWoman.get((int) random);
        listWoman.remove(woman);
        return woman;
    }

    @Override
    public Integer pregnant(Settlement settlement, Integer countPregnant) {
        List<Woman> listWoman = womanService.getWomanMarriedNotPregnant(settlement.getId());
        for (int i = 0; i < listWoman.size(); i++) {
            int random = (int) (Math.random() * (10 + 2 * listWoman.get(i).getCountBaby()));
            if (random == 0) {
                countPregnant++;
                Woman currentWoman = listWoman.get(i);
                currentWoman.setPregnant(true);
                womanService.saveWoman(currentWoman);
            }
        }
        return countPregnant;
    }

    @Override
    public Integer childbirth(Settlement settlement, Integer countBaby) {
        List<Woman> listWoman = womanService.getWomanPregnant(settlement.getId());
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
                womanService.saveWoman(currentWoman);
                this.addNewIndividual(settlement, 0);
            } else {
                Woman currentWoman = listWoman.get(i);
                currentWoman.setPregnantDuration(gestation);
                womanService.saveWoman(currentWoman);
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
            manService.addMan(man);
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
            womanService.addWoman(woman);
            settlement.getWomen().add(woman);
        }
    }

    @Override
    public void pregnantRecess(Settlement settlement) {
        List<Woman> womanList = womanService.getWomanOnPregnantRecess(settlement.getId());
        for (int i = 0; i < womanList.size(); i++) {
            Woman woman = womanList.get(i);
            woman.setPregnantRecess(woman.getPregnantRecess() - 1);
            womanService.saveWoman(woman);
        }
    }
}
