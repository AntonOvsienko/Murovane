package com.sale.ticket.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "woman_list")
@EqualsAndHashCode (of = "id")
public class Woman extends Individual {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "name_id")
    private WomanName name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "settlement_id")
    private Settlement settlement;

    //    @ManyToMany
    //    @JoinTable (name = "woman_negative",
    //            joinColumns = {@JoinColumn (name = "woman_list")},
    //            inverseJoinColumns = {@JoinColumn(name = "negative_traits")})
    //    private List<NegativeTrait> individualNegative = new ArrayList<>();
    //
    //    @ManyToMany
    //    @JoinTable (name = "woman_positive",
    //            joinColumns = {@JoinColumn(name = "woman_list")},
    //            inverseJoinColumns = {@JoinColumn(name = "positive_traits")})
    //    private List<PositiveTrait> individualPositive = new ArrayList<>();

}
