package com.sale.ticket.murovane.service.impl;

import com.sale.ticket.murovane.converters.SettlementConverter;
import com.sale.ticket.murovane.model.Man;
import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.repository.ManRepository;
import com.sale.ticket.murovane.service.ManService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ManServiceImpl implements ManService {

    private final ManRepository manRepository;

    private final SettlementConverter settlementConverter;

    @Transactional
    public Integer addMan(Man man) {
        man = manRepository.save(man);
        return man.getId();
    }

    @Override
    public List<Man> getManUnderMarried(Integer id) {
        List<Man> men = manRepository.getManUnderMarried(id);
        if (men.size() != 0) {
            return men.stream().filter(man -> settlementConverter
                            .getAge(man.getDateBorn(), man.getSettlement().getSettlementTime()) > 15)
                    .sorted((man1, man2) -> {
                        if (man1.getDateBorn().equals(man2.getDateBorn())){
                            return 0;
                        }
                        if (man1.getDateBorn().isBefore(man2.getDateBorn())){
                            return 1;
                        } else {
                            return -1;
                        }
                    })
                .collect(Collectors.toList());
        } else {
            return men;
        }
    }

    @Override
    public List<Man> getManUnderMarried(Settlement settlement) {
        List<Man> men = settlement.getMen()
                .stream()
                .filter(man ->Objects.isNull(man.getWife()))
                .collect(Collectors.toList());
        if (men.size() != 0) {
            return men.stream().filter(man -> settlementConverter
                            .getAge(man.getDateBorn(), man.getSettlement().getSettlementTime()) > 15)
                    .sorted((man1, man2) -> {
                        if (man1.getDateBorn().equals(man2.getDateBorn())){
                            return 0;
                        }
                        if (man1.getDateBorn().isBefore(man2.getDateBorn())){
                            return 1;
                        } else {
                            return -1;
                        }
                    })
                    .collect(Collectors.toList());
        } else {
            return men;
        }
    }

    @Override
    public void saveMan(Man man) {
        manRepository.save(man);
    }

    @Override
    public void deleteMan(Man man) {
        manRepository.deleteById(man.getId());
    }


}
