package com.sale.ticket.murovane.service.impl;

import com.sale.ticket.murovane.model.Surname;
import com.sale.ticket.murovane.repository.SurnameRepository;
import com.sale.ticket.murovane.service.SurnameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SurnameServiceImpl implements SurnameService {

    private final SurnameRepository surnameRepository;

    @Override
    public List<Surname> getListSurname() {
        return surnameRepository.findAll();
    }
}
