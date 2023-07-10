package com.sale.ticket.task.converters;

import com.sale.ticket.task.domen.model.ManOverview;
import com.sale.ticket.task.domen.model.WomanOverview;
import com.sale.ticket.task.model.Man;
import com.sale.ticket.task.model.Woman;

import java.time.LocalDate;
import java.util.List;

public interface SettlementConverter {

    List<WomanOverview> convertWomanOverview(List<Woman> women);

    List<ManOverview> convertManOverview(List<Man> men);

    List<ManOverview> convertBoyOverview(List<Man> men);

    List<WomanOverview> convertGirlOverview(List<Woman> women);

    Integer getAge(LocalDate dateBorn, LocalDate settlement);
}
