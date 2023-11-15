package com.sale.ticket.murovane.converters.impl;

import com.sale.ticket.murovane.converters.SettlementConverter;
import com.sale.ticket.murovane.domen.model.ManOverview;
import com.sale.ticket.murovane.domen.model.SettlementOverview;
import com.sale.ticket.murovane.domen.model.WomanOverview;
import com.sale.ticket.murovane.model.Man;
import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.model.Woman;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static com.sale.ticket.murovane.constants.TimeConstants.TIME_MULTIPLAYER;


@Component
public class SettlementConverterImpl implements Converter<Settlement, SettlementOverview>, SettlementConverter {

    @Override
    public SettlementOverview convert(Settlement settlement) {
        SettlementOverview settlementOverview = new SettlementOverview();
        settlementOverview.setId(settlement.getId());
        settlementOverview.setLastUpdateTime(getTimeLastUpdate(settlement));
        settlementOverview.setName(settlement.getName());
        settlementOverview.setMen(convertManOverview(settlement.getMen()));
        settlementOverview.setBoy(convertBoyOverview(settlement.getMen()));
        settlementOverview.setWomen(convertWomanOverview(settlement.getWomen()));
        settlementOverview.setGirl(convertGirlOverview(settlement.getWomen()));
        settlementOverview.setMedianAge(getMedianAge(settlementOverview));
        return settlementOverview;
    }

    private String getTimeLastUpdate(Settlement settlement) {
        long seconds = (LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(0)) -
                settlement.getLastTime().toEpochSecond(ZoneOffset.ofHours(0))) * TIME_MULTIPLAYER;
        int month = (int) (seconds / (24 * 60 * 60) * 12) / 365;
        int years = (int) (seconds / (24 * 60 * 60)) / 365;
        while (month > 12) {
            month = month - 12;
        }
        return "С последнего обновление прошло " + years + getYearFormat(years) + " и " + month + getMonthFormat(month);
    }

    private String getMonthFormat(int month) {
        if (month == 1) {
            return " месяц";
        }
        if (month > 0 && month < 5) {
            return " месяца";
        }
        return " месяцев";
    }

    private String getYearFormat(int years) {
        if (years == 1) {
            return " год";
        }
        if (years > 0 && years < 5) {
            return " года";
        }
        return " лет";
    }

    public Integer getMedianAge(SettlementOverview settlement) {
        Integer totalWomanAge = settlement.getWomen().stream().mapToInt(WomanOverview::getAge).sum();
        Integer totalGirlAge = settlement.getGirl().stream().mapToInt(WomanOverview::getAge).sum();
        Integer totalManAge = settlement.getMen().stream().mapToInt(ManOverview::getAge).sum();
        Integer totalBoyAge = settlement.getBoy().stream().mapToInt(ManOverview::getAge).sum();
        return (totalManAge + totalWomanAge + totalBoyAge + totalGirlAge) /
                (settlement.getMen().size() + settlement.getWomen().size() +
                        settlement.getBoy().size() + settlement.getGirl().size());
    }

    public List<WomanOverview> convertWomanOverview(List<Woman> women) {
        List<WomanOverview> womanOverviewList = new ArrayList<>();
        for (Woman woman : women) {
            if (getAge(woman.getDateBorn(),woman.getSettlement().getSettlementTime()) > 15) {
                WomanOverview womanOverview = createWomanOverview(woman);
                womanOverviewList.add(womanOverview);
            }
        }
        return womanOverviewList;
    }

    public List<ManOverview> convertManOverview(List<Man> men) {
        List<ManOverview> manOverviewList = new ArrayList<>();
        for (Man man : men) {
            if (getAge(man.getDateBorn(),man.getSettlement().getSettlementTime()) > 15) {
                ManOverview manOverview = createManOverview(man);
                manOverviewList.add(manOverview);
            }
        }
        return manOverviewList;
    }

    public List<ManOverview> convertBoyOverview(List<Man> men) {
        List<ManOverview> manOverviewList = new ArrayList<>();

        for (Man man : men) {
            if (getAge(man.getDateBorn(),man.getSettlement().getSettlementTime()) < 16) {
                ManOverview manOverview = createManOverview(man);
                manOverviewList.add(manOverview);
            }
        }
        return manOverviewList;
    }

    public List<WomanOverview> convertGirlOverview(List<Woman> women) {
        List<WomanOverview> womanOverviewList = new ArrayList<>();
        for (Woman woman : women) {
            if (getAge(woman.getDateBorn(),woman.getSettlement().getSettlementTime()) < 16) {
                WomanOverview womanOverview = createWomanOverview(woman);
                womanOverviewList.add(womanOverview);
            }
        }
        return womanOverviewList;
    }

    private ManOverview createManOverview(Man man) {
        ManOverview manOverview = new ManOverview();
        manOverview.setFullName(man.getFullName());
        manOverview.setId(man.getId());
        manOverview.setHealth(man.getHealth());
        manOverview.setDateBorn(man.getDateBorn());
        manOverview.setSettlement(man.getSettlement());
        manOverview.setAge(getAge(man.getDateBorn(),man.getSettlement().getSettlementTime()));
        return manOverview;
    }

    private WomanOverview createWomanOverview(Woman woman) {
        WomanOverview womanOverview = new WomanOverview();
        womanOverview.setFullName(woman.getFullName());
        womanOverview.setId(woman.getId());
        womanOverview.setHealth(woman.getHealth());
        womanOverview.setDateBorn(woman.getDateBorn());
        womanOverview.setSettlement(woman.getSettlement());
        womanOverview.setAge(getAge(woman.getDateBorn(),woman.getSettlement().getSettlementTime()));
        return womanOverview;
    }

    public Integer getAge(LocalDate dateBorn, LocalDate settlementTime) {
        int age = settlementTime.getYear() - dateBorn.getYear();
        if (dateBorn.getMonth().getValue() > settlementTime.getMonth().getValue()) {
            age--;
        }
        if (dateBorn.getMonth().getValue() == settlementTime.getMonth().getValue()) {
            if (dateBorn.getDayOfYear() > settlementTime.getDayOfYear()) {
                age--;
            }
        }
        return age;
    }

}
