package com.sale.ticket.murovane.service;

import com.sale.ticket.murovane.model.Settlement;
import com.sale.ticket.murovane.model.Woman;

import java.util.List;

public interface WomanService {

    Integer addWoman(Woman woman);

    void saveWoman (Woman woman);

    List<Woman> getWomenUnderMarried(Integer id);
    List<Woman> getWomenUnderMarried(Settlement settlement);

    List<Woman> getWomanMarriedNotPregnant(Integer id);
    List<Woman> getWomanMarriedNotPregnant(Settlement settlement);

    List<Woman> getWomanPregnant(Integer id);

    List<Woman> getWomanPregnant(Settlement settlement);

    List<Woman> getWomanOnPregnantRecess(Integer id);
    List<Woman> getWomanOnPregnantRecess(Settlement settlement);

    void deleteWoman(Woman woman);
}
