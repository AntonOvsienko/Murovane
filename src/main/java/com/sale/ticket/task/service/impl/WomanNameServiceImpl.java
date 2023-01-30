package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.WomanName;
import com.sale.ticket.task.repository.WomanNameRepository;
import com.sale.ticket.task.service.WomanNameService;
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
