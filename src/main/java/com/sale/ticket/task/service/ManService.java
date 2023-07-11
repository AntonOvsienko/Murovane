package com.sale.ticket.task.service;

import com.sale.ticket.task.model.Man;
import com.sale.ticket.task.model.Settlement;

import java.util.List;

public interface ManService {

    Integer addMan(Man man);

    List<Man> getManUnderMarried(Integer id);

    List<Man> getManUnderMarried(Settlement settlement);

    void saveMan (Man man);

    void deleteMan(Man man);
}
