package com.sale.ticket.Murovane.service;

import com.sale.ticket.Murovane.model.Man;
import com.sale.ticket.Murovane.model.Settlement;

import java.util.List;

public interface ManService {

    Integer addMan(Man man);

    List<Man> getManUnderMarried(Integer id);

    List<Man> getManUnderMarried(Settlement settlement);

    void saveMan (Man man);

    void deleteMan(Man man);
}
