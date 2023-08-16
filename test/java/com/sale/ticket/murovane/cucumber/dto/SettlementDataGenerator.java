package com.sale.ticket.murovane.cucumber.dto;

import com.sale.ticket.murovane.model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SettlementDataGenerator {

    private final IndividualDataGenerator individualDataGenerator = new IndividualDataGenerator();

    public Settlement simpleSettlement() {
        Settlement settlement = new Settlement();
        settlement.setSettlementTime(LocalDate.of(1940, 8, 12));
        settlement.setLastTime(LocalDateTime.of(1940, 8, 5, 0, 0));
        return settlement;
    }

    public Settlement createSettlementForMarried(int count) {
        Settlement settlement = new Settlement();
        settlement.setSettlementTime(LocalDate.of(1940, 8, 12));
        settlement.setLastTime(LocalDateTime.of(1940, 8, 5, 0, 0));
        List<ManName> manNameList = individualDataGenerator.createListManName();
        List<WomanName> womanNameList = individualDataGenerator.createListWomanName();
        List<Surname> surnameList = individualDataGenerator.createListSurname();
        for (int i = 0; i < count; i++) {
            int sex = (int) (Math.random() * 2);
            int manListCount = (int) (Math.random() * manNameList.size());
            int womanListCount = (int) (Math.random() * womanNameList.size());
            int surnameListCount = (int) (Math.random() * surnameList.size());
            if (sex == 1) {
                Man man = new Man();
                man.setId(i);
                man.setHealth(5);
                man.setName(manNameList.get(manListCount));
                man.setSurname(surnameList.get(surnameListCount));
                man.setSettlement(settlement);
                man.setDateBorn(LocalDate.of(1910, 8, 12));
                settlement.getMen().add(man);
            } else {
                Woman woman = new Woman();
                woman.setId(i);
                woman.setHealth(5);
                woman.setName(womanNameList.get(womanListCount));
                woman.setSurname(surnameList.get(surnameListCount));
                woman.setSettlement(settlement);
                woman.setDateBorn(LocalDate.of(1910, 8, 12));
                settlement.getWomen().add(woman);
            }
        }
        return settlement;
    }

    public Settlement createSettlementForPregnant(Integer wife, Integer nowife) {
        Settlement settlement = new Settlement();
        settlement.setSettlementTime(LocalDate.of(1940, 8, 12));
        settlement.setLastTime(LocalDateTime.of(1940, 8, 5, 0, 0));
        List<ManName> manNameList = individualDataGenerator.createListManName();
        List<WomanName> womanNameList = individualDataGenerator.createListWomanName();
        List<Surname> surnameList = individualDataGenerator.createListSurname();
        for (int i = 0; i < wife; i++) {
            int manListCount = (int) (Math.random() * manNameList.size());
            int womanListCount = (int) (Math.random() * womanNameList.size());
            int surnameListCount = (int) (Math.random() * surnameList.size());
            Man man = new Man();
            man.setHealth(5);
            man.setName(manNameList.get(manListCount));
            man.setSurname(surnameList.get(surnameListCount));
            man.setSettlement(settlement);
            man.setDateBorn(LocalDate.of(1910, 8, 12));

            Woman woman = new Woman();
            woman.setHealth(5);
            woman.setName(womanNameList.get(womanListCount));
            woman.setSurname(surnameList.get(surnameListCount));
            woman.setSettlement(settlement);
            woman.setDateBorn(LocalDate.of(1910, 8, 12));
            woman.setPregnant(Boolean.FALSE);
            woman.setPregnantRecess(0);
            woman.setCountBaby(0);

            woman.setHusband(man);
            man.setWife(woman);

            settlement.getMen().add(man);
            settlement.getWomen().add(woman);
        }

        for (int i = 0; i < nowife; i++) {
            int womanListCount = (int) (Math.random() * womanNameList.size());
            int surnameListCount = (int) (Math.random() * surnameList.size());
            Woman woman = new Woman();
            woman.setHealth(5);
            woman.setName(womanNameList.get(womanListCount));
            woman.setSurname(surnameList.get(surnameListCount));
            woman.setSettlement(settlement);
            woman.setDateBorn(LocalDate.of(1910, 8, 12));
            woman.setPregnant(Boolean.FALSE);
            woman.setPregnantRecess(0);
            woman.setCountBaby(0);

            settlement.getWomen().add(woman);
        }
        return settlement;
    }

    public Settlement createSettlementForChildBirth(int arg0, int arg1) {
        Settlement settlement = new Settlement();
        settlement.setSettlementTime(LocalDate.of(1940, 8, 12));
        settlement.setLastTime(LocalDateTime.of(1940, 8, 5, 0, 0));
        List<WomanName> womanNameList = individualDataGenerator.createListWomanName();
        List<Surname> surnameList = individualDataGenerator.createListSurname();
        for (int i = 0; i < arg0; i++) {
            int womanListCount = (int) (Math.random() * womanNameList.size());
            int surnameListCount = (int) (Math.random() * surnameList.size());

            Woman woman = new Woman();
            woman.setHealth(5);
            woman.setName(womanNameList.get(womanListCount));
            woman.setSurname(surnameList.get(surnameListCount));
            woman.setSettlement(settlement);
            woman.setDateBorn(LocalDate.of(1910, 8, 12));
            woman.setPregnant(Boolean.TRUE);
            woman.setPregnantRecess(0);
            woman.setCountBaby(0);
            woman.setPregnantDuration(i);

            settlement.getWomen().add(woman);
        }

        for (int i = 0; i < arg1; i++) {
            int womanListCount = (int) (Math.random() * womanNameList.size());
            int surnameListCount = (int) (Math.random() * surnameList.size());
            Woman woman = new Woman();
            woman.setHealth(5);
            woman.setName(womanNameList.get(womanListCount));
            woman.setSurname(surnameList.get(surnameListCount));
            woman.setSettlement(settlement);
            woman.setDateBorn(LocalDate.of(1910, 8, 12));
            woman.setPregnant(Boolean.FALSE);
            woman.setPregnantRecess(0);
            woman.setCountBaby(0);

            settlement.getWomen().add(woman);
        }
        return settlement;
    }
}
