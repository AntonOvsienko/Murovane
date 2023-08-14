package com.sale.ticket.murovane.service.impl;

import com.sale.ticket.murovane.model.WomanName;
import com.sale.ticket.murovane.repository.WomanNameRepository;
import com.sale.ticket.murovane.service.WomanNameService;
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
