package com.sale.ticket.task.service;

import com.sale.ticket.task.model.Man;

import java.util.List;

public interface ManService {

    Integer addMan(Man man);

    List<Man> getManUnderMarried(Integer id);

    void saveMan (Man man);
}
