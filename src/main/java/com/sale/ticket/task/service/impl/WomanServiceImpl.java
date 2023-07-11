package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.converters.SettlementConverter;
import com.sale.ticket.task.model.Settlement;
import com.sale.ticket.task.model.Woman;
import com.sale.ticket.task.repository.WomanRepository;
import com.sale.ticket.task.service.WomanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WomanServiceImpl implements WomanService {

    private final WomanRepository womanRepository;

    private final SettlementConverter settlementConverter;

    public Integer addWoman(Woman woman) {
        woman = womanRepository.save(woman);
        return woman.getId();
    }

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
    public List<Woman> getWomenUnderMarried(Settlement settlement) {
        List<Woman> women = settlement.getWomen().stream()
                .filter(woman -> Objects.isNull(woman.getHusband()))
                .collect(Collectors.toList());
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
    public List<Woman> getWomanMarriedNotPregnant(Settlement settlement) {
        return settlement.getWomen().stream()
                .filter(women -> Objects.nonNull(women.getHusband()))
                .filter(women -> women.getPregnant().equals(Boolean.FALSE))
                .filter(women -> women.getPregnantRecess() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Woman> getWomanPregnant(Integer id) {
        return womanRepository.getWomanPregnant(id);
    }

    @Override
    public List<Woman> getWomanPregnant(Settlement settlement) {
        return settlement.getWomen().stream()
                .filter(women -> women.getPregnant().equals(Boolean.TRUE))
                .collect(Collectors.toList());
    }

    @Override
    public List<Woman> getWomanOnPregnantRecess(Integer id) {
        return womanRepository.getWomanOnPregnantRecess(id);
    }

    @Override
    public List<Woman> getWomanOnPregnantRecess(Settlement settlement) {
        return settlement.getWomen().stream()
                .filter(women -> women.getPregnantRecess() > 0)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteWoman(Woman woman) {
        womanRepository.deleteById(woman.getId());
    }
}
