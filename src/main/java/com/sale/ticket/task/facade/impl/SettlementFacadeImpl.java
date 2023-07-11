package com.sale.ticket.task.facade.impl;

import com.sale.ticket.task.converters.SettlementConverter;
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
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SettlementFacadeImpl implements SettlementFacade {

    @Resource
    private SettlementService settlementService;
    @Resource
    private SettlementRepository settlementRepository;
    @Resource
    private SettlementConverter settlementConverter;
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
        settlement.setLastTime(LocalDateTime.now());
        settlement.setSettlementTime(LocalDate.of(100, 1, 1));
        for (int i = 0; i < quantity; i++) {
            addNewIndividual(settlement, 45);
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
            countMarried = married(settlement, countMarried);
            countPregnant = pregnant(settlement, countPregnant);
            countBaby = childbirth(settlement, countBaby);
            pregnantRecess(settlement);
            countDead = dead(settlement, countDead);
            settlement = settlementService.updateSettler(settlement);
//            settlementService.createLogHistory(settlement);
        }

        messages.add("Кол-во браков за истекший период - " + countMarried);
        messages.add("Кол-во новых беременностей за истекший период - " + countPregnant);
        messages.add("Кол-во новорожденных за истекший период - " + countBaby);
        messages.add("Кол-во погибних за истекший период - " + countDead);
        return settlement;
    }

    @Transactional
    private Integer dead(Settlement settlement, Integer countDead) {
        List<Man> men = new ArrayList<>(settlement.getMen());
        List<Woman> women = new ArrayList<>(settlement.getWomen());
        for (int i = 0; i < men.size(); i++) {
            Man man = men.get(i);
            int age = settlementConverter.getAge(man.getDateBorn(), man.getSettlement().getSettlementTime());
            if (age > 80) {
                countDead++;
                settlement.getMen().remove(man);
                manService.deleteMan(man);
            }
        }
        for (int i = 0; i < women.size(); i++) {
            Woman woman = women.get(i);
            int age = settlementConverter.getAge(woman.getDateBorn(), woman.getSettlement().getSettlementTime());
            if (age > 80) {
                countDead++;
                settlement.getWomen().remove(woman);
                womanService.deleteWoman(woman);
            }
        }
        return countDead;
    }

    private void pregnantRecess(Settlement settlement) {
        List<Woman> womanList = womanService.getWomanOnPregnantRecess(settlement);
        for (int i = 0; i < womanList.size(); i++) {
            Woman woman = womanList.get(i);
            woman.setPregnantRecess(woman.getPregnantRecess() - 1);
            int index = settlement.getWomen().indexOf(woman);
            settlement.getWomen().set(index,woman);
        }
    }

    private Integer childbirth(Settlement settlement, Integer countBaby) {
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
                int index = settlement.getWomen().indexOf(currentWoman);
                settlement.getWomen().set(index,currentWoman);
                addNewIndividual(settlement, 0);
            } else {
                Woman currentWoman = listWoman.get(i);
                currentWoman.setPregnantDuration(gestation);
                int index = settlement.getWomen().indexOf(currentWoman);
                settlement.getWomen().set(index,currentWoman);
            }
        }
        return countBaby;
    }

    private Integer pregnant(Settlement settlement, Integer countPregnant) {
        List<Woman> listWoman = womanService.getWomanMarriedNotPregnant(settlement);
        for (int i = 0; i < listWoman.size(); i++) {
            int random = (int) (Math.random() * (10 + 2 * listWoman.get(i).getCountBaby()));
            if (random == 0) {
                countPregnant++;
                Woman currentWoman = listWoman.get(i);
                currentWoman.setPregnant(true);
                int index = settlement.getWomen().indexOf(currentWoman);
                settlement.getWomen().set(index, currentWoman);
            }
        }
        return countPregnant;
    }

    private Integer married(Settlement settlement, Integer countMarried) {
        List<Man> listMan = manService.getManUnderMarried(settlement);
        List<Woman> listWoman = womanService.getWomenUnderMarried(settlement);
        for (int i = 0; i < listMan.size(); i++) {
            if (listWoman.size() == 0) {
                break;
            }
            int random = (int) (Math.random() * 5);
            if (random == 0) {
                Man currentMan = listMan.get(i);
                currentMan.setWife(getWife(listWoman));
                int indexMan = settlement.getMen().indexOf(currentMan);
                settlement.getMen().set(indexMan,currentMan);
                int indexWoman = settlement.getWomen().indexOf(currentMan.getWife());
                settlement.getWomen().set(indexWoman,currentMan.getWife());
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

    private void updateDate(Settlement settlement, Integer countMonth) {
        LocalDate date = settlement.getSettlementTime();
        settlement.setSettlementTime(date.plusMonths(countMonth));
        settlement.setLastTime(LocalDateTime.now());
    }

    private Integer getCountMonth(Settlement settlement) {
        long seconds = (LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0)) - settlement.getLastTime().toEpochSecond(ZoneOffset.ofHours(0)));
        return (int) (seconds * 12 / 60);
    }

    private void addNewIndividual(Settlement settlement, Integer maxAge) {
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
}
