package com.sale.ticket.murovane.domen.model;

import com.sale.ticket.murovane.model.Woman;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WomanOverview extends Woman {

    private Integer age;
    private String fullName;

}
