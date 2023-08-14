package com.sale.ticket.murovane.converters;

import com.sale.ticket.murovane.domen.model.ManOverview;
import com.sale.ticket.murovane.domen.model.WomanOverview;
import com.sale.ticket.murovane.model.Man;
import com.sale.ticket.murovane.model.Woman;

import java.time.LocalDate;
import java.util.List;

public interface SettlementConverter {

    List<WomanOverview> convertWomanOverview(List<Woman> women);

    List<ManOverview> convertManOverview(List<Man> men);

    List<ManOverview> convertBoyOverview(List<Man> men);

    List<WomanOverview> convertGirlOverview(List<Woman> women);

    Integer getAge(LocalDate dateBorn, LocalDate settlement);
}
