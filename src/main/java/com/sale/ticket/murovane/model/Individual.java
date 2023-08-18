package com.sale.ticket.murovane.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EqualsAndHashCode
public abstract class Individual {

    @Column(name = "health")
    private Integer health;

    @Column(name = "date_born")
    private LocalDate dateBorn;

    @ManyToOne
    @JoinColumn(name = "surname_id")
    private Surname surname;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @DateTimeFormat(pattern = "dd-MMM-YYYY")
    public LocalDate getDateBorn() {
        return dateBorn;
    }
}
