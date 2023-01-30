package com.sale.ticket.task.service.impl;

import com.sale.ticket.task.model.Surname;
import com.sale.ticket.task.repository.SurnameRepository;
import com.sale.ticket.task.service.SurnameService;
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
