package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.ManName;
import com.sale.ticket.task.repository.ManNameRepository;
import com.sale.ticket.task.service.ManNameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ManNameServiceImpl implements ManNameService {

    private final ManNameRepository manNameRepository;

    @Override
    public List<ManName> getListManName() {
        return manNameRepository.findAll();
    }
}
