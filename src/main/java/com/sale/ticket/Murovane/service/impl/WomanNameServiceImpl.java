package com.sale.ticket.Murovane.service.impl;

import com.sale.ticket.Murovane.model.WomanName;
import com.sale.ticket.Murovane.repository.WomanNameRepository;
import com.sale.ticket.Murovane.service.WomanNameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class WomanNameServiceImpl implements WomanNameService {

    private final WomanNameRepository womanNameRepository;

    @Override
    public List<WomanName> getListWomanName() {
        return womanNameRepository.findAll();
    }
}
