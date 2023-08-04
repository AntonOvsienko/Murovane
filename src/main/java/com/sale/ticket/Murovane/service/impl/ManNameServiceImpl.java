package com.sale.ticket.Murovane.service.impl;

import com.sale.ticket.Murovane.model.ManName;
import com.sale.ticket.Murovane.repository.ManNameRepository;
import com.sale.ticket.Murovane.service.ManNameService;
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
