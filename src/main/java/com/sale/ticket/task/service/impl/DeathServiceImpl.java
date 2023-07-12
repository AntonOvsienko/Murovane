package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.converters.SettlementConverter;
import com.sale.ticket.task.model.Man;
import com.sale.ticket.task.model.Settlement;
import com.sale.ticket.task.model.Woman;
import com.sale.ticket.task.repository.SettlementRepository;
import com.sale.ticket.task.service.DeathService;
import com.sale.ticket.task.service.ManService;
import com.sale.ticket.task.service.WomanService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class DeathServiceImpl implements DeathService {

    @Resource
    private SettlementConverter settlementConverter;
    @Resource
    private ManService manService;
    @Resource
    private WomanService womanService;

    @Override
    public Integer dead(Settlement settlement, Integer countDead) {
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
}
