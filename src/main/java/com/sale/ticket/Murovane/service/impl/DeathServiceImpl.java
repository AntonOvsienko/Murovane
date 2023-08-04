package com.sale.ticket.Murovane.service.impl;

import com.sale.ticket.Murovane.converters.SettlementConverter;
import com.sale.ticket.Murovane.model.Man;
import com.sale.ticket.Murovane.model.Settlement;
import com.sale.ticket.Murovane.model.Woman;
import com.sale.ticket.Murovane.service.DeathService;
import com.sale.ticket.Murovane.service.ManService;
import com.sale.ticket.Murovane.service.WomanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class DeathServiceImpl implements DeathService {

    @Resource
    private SettlementConverter settlementConverter;

    @Override
    public Integer dead(Settlement settlement, Integer countDead) {
        List<Man> men = new ArrayList<>(settlement.getMen());
        List<Woman> women = new ArrayList<>(settlement.getWomen());
        for (int i = 0; i < men.size(); i++) {
            Man man = men.get(i);
            int age = settlementConverter.getAge(man.getDateBorn(), man.getSettlement().getSettlementTime());
            if (age > 80) {
                countDead++;
                if (Objects.nonNull(man.getWife())){
                    man.getWife().setHusband(null);
                }
                settlement.getMen().remove(man);
            }
        }
        for (int i = 0; i < women.size(); i++) {
            Woman woman = women.get(i);
            int age = settlementConverter.getAge(woman.getDateBorn(), woman.getSettlement().getSettlementTime());
            if (age > 80) {
                countDead++;
                if (Objects.nonNull(woman.getHusband())){
                    woman.getHusband().setWife(null);
                }
                settlement.getWomen().remove(woman);
            }
        }
        return countDead;
    }
}
