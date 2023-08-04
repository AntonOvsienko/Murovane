package com.sale.ticket.Murovane.service;

import com.sale.ticket.Murovane.model.Settlement;

public interface BurnService {

    Integer married (Settlement settlement, Integer countMarried);

    Integer pregnant(Settlement settlement, Integer countPregnant);

    Integer childbirth(Settlement settlement, Integer countBaby);

    void pregnantRecess(Settlement settlement);

    void addNewIndividual(Settlement settlement, Integer age);

}
