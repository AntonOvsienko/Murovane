package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Man;
import com.sale.ticket.task.model.Woman;
import com.sale.ticket.task.repository.ManRepository;
import com.sale.ticket.task.repository.WomanRepository;
import com.sale.ticket.task.service.WomanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class WomanServiceImpl implements WomanService {

    private final WomanRepository womanRepository;

    @Transactional
    public Integer addWoman(Woman woman) {
        woman = womanRepository.saveAndFlush(woman);
        return woman.getId();
    }

}
