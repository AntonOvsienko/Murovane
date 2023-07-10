package com.sale.ticket.task.service;

import com.sale.ticket.task.model.Woman;

import java.util.List;

public interface WomanService {

    Integer addWoman(Woman woman);

    void saveWoman (Woman woman);

    List<Woman> getWomenUnderMarried(Integer id);

    List<Woman> getWomanMarriedNotPregnant(Integer id);

    List<Woman> getWomanPregnant(Integer id);

    List<Woman> getWomanOnPregnantRecess(Integer id);

    void deleteWoman(Woman woman);
}
