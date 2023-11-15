package com.sale.ticket.murovane.domen.model;

import com.sale.ticket.murovane.model.Man;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManOverview extends Man {

    private Integer age;
    private String fullName;
}
