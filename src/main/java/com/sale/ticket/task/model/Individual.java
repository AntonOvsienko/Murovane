package com.sale.ticket.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Individual {

    @Column (name = "health")
    private Integer health;

    @Column (name = "date_born")
    private Date dateBorn;

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

}
