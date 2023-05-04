package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.converters.SettlementConverter;
import com.sale.ticket.task.model.Man;
import com.sale.ticket.task.model.Woman;
import com.sale.ticket.task.repository.WomanRepository;
import com.sale.ticket.task.service.WomanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WomanServiceImpl implements WomanService {

    private final WomanRepository womanRepository;

    private final SettlementConverter settlementConverter;

    @Transactional
    public Integer addWoman(Woman woman) {
        woman = womanRepository.save(woman);
        return woman.getId();
    }

    @Transactional
    @Override
    public void saveWoman(Woman woman) {
        womanRepository.saveAndFlush(woman);
    }

    @Override
    public List<Woman> getWomenUnderMarried(Integer id) {
        List<Woman> women = womanRepository.getWomanUnderMarried(id);
        if (women.size() != 0) {
            return women.stream().filter(woman -> settlementConverter
                            .getAge(woman.getDateBorn(), woman.getSettlement().getSettlementTime()) > 15)
                    .sorted((woman1, woman2) -> {
                        if (woman1.getDateBorn().equals(woman2.getDateBorn())){
                            return 0;
                        }
                        if (woman1.getDateBorn().isBefore(woman2.getDateBorn())){
                            return 1;
                        } else {
                            return -1;
                        }
                    })
                    .collect(Collectors.toList());
        } else {
            return women;
        }
    }

    @Override
    public List<Woman> getWomanMarriedNotPregnant(Integer id) {
        return womanRepository.getWomanMarriedNotPregnant(id);
    }

    @Override
    public List<Woman> getWomanPregnant(Integer id) {
        return womanRepository.getWomanPregnant(id);
    }
}
