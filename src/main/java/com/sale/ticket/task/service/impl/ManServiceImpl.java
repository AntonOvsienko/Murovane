package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Man;
import com.sale.ticket.task.repository.ManRepository;
import com.sale.ticket.task.repository.SettlementRepository;
import com.sale.ticket.task.service.ManService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ManServiceImpl implements ManService {

    private final ManRepository manRepository;

    @Transactional
    public Integer addMan(Man man) {
        man = manRepository.save(man);
        return man.getId();
    }

}
