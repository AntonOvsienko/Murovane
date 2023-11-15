package com.sale.ticket.murovane.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Individual {

    @Column(name = "health")
    private Integer health;

    @Column(name = "date_born")
    private LocalDate dateBorn;

    @EqualsAndHashCode.Include
    @Column(name = "full_name")
    private String fullName;

    @DateTimeFormat(pattern = "dd-MMM-YYYY")
    public LocalDate getDateBorn() {
        return dateBorn;
    }
}
