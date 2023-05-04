package com.sale.ticket.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Individual {

    @Column (name = "health")
    private Integer health;

    @Column (name = "date_born")
    private LocalDate dateBorn;

    @ManyToOne
    @JoinColumn (name = "surname_id")
    private Surname surname;

    @ManyToOne
    @JoinColumn (name = "profession_id")
    private Profession profession;

//    @ManyToMany
//    @JoinTable (name = "individual_negative",
//            joinColumns = {@JoinColumn (name = "individual_list")},
//            inverseJoinColumns = {@JoinColumn(name = "negative_traits")})
//    private List<NegativeTrait> individualNegative = new ArrayList<>();
//
//    @ManyToMany
//    @JoinTable (name = "individual_positive",
//            joinColumns = {@JoinColumn(name = "individual_list")},
//            inverseJoinColumns = {@JoinColumn(name = "positive_traits")})
//    private List<PositiveTrait> individualPositive = new ArrayList<>();

    @DateTimeFormat (pattern="dd-MMM-YYYY")
    public LocalDate getDateBorn() {
        return dateBorn;
    }
}
