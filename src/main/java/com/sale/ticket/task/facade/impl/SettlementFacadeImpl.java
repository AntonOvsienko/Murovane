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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
    public Settlement createSettler(Integer quantity) {
        Settlement settlement = addSettler(quantity);
        return settlement;
    }

    private Settlement addSettler(Integer quantity) {
        Settlement settlement = settlementRepository.save(new Settlement());
        List<Man> manList = new ArrayList<>();
        List<Woman> womanList = new ArrayList<>();
        List<ManName> manNameList = manNameService.getListManName();
        List<WomanName> womanNameList = womanNameService.getListWomanName();
        List<Surname> surnameList = surnameService.getListSurname();
        settlement.setStartTime(Calendar.getInstance().getTime());
        for (int i = 0; i < quantity; i++) {
            addNewIndividual(settlement, manNameList, womanNameList, surnameList);
        }
        settlement.setMen(manList);
        settlement.setWomen(womanList);
        return settlement;
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
            man.setDateBorn(bornDate(age));
            man.setSettlement(settlement);
            manService.addMan(man);
        } else {
            Woman woman = new Woman();
            woman.setName(womanNameList.get(womanListCount));
            woman.setSurname(surnameList.get(surnameListCount));
            woman.setHealth(5);
            woman.setDateBorn(bornDate(age));
            woman.setSettlement(settlement);
            womanService.addWoman(woman);
        }
    }

    private Date bornDate(int age) {
        int hundredYears = 365 / 2;
        LocalDate localDate = LocalDate.ofEpochDay(ThreadLocalRandom.current().nextInt(-hundredYears, hundredYears));
        Calendar calendar = Calendar.getInstance();
        return new Date(calendar.getTime().getYear() - age, localDate.getMonthValue(), localDate.getDayOfMonth());

    }
}
