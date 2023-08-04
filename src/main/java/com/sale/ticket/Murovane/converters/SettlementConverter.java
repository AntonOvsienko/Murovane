package com.sale.ticket.Murovane.converters;

import com.sale.ticket.Murovane.domen.model.ManOverview;
import com.sale.ticket.Murovane.domen.model.WomanOverview;
import com.sale.ticket.Murovane.model.Man;
import com.sale.ticket.Murovane.model.Woman;

import java.time.LocalDate;
import java.util.List;

public interface SettlementConverter {

    List<WomanOverview> convertWomanOverview(List<Woman> women);

    List<ManOverview> convertManOverview(List<Man> men);

    List<ManOverview> convertBoyOverview(List<Man> men);

    List<WomanOverview> convertGirlOverview(List<Woman> women);

    Integer getAge(LocalDate dateBorn, LocalDate settlement);
}
