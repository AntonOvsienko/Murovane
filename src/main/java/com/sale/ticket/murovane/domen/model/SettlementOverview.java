package com.sale.ticket.murovane.domen.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettlementOverview {

    private Integer id;

    private String name;

    private String lastUpdateTime;

    private List<ManOverview> men = new ArrayList<>();

    private List<ManOverview> boy = new ArrayList<>();

    private List<WomanOverview> women = new ArrayList<>();

    private List<WomanOverview> girl = new ArrayList<>();

    private Integer medianAge;

}
