package com.sale.ticket.murovane.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "woman_list")
@EqualsAndHashCode(callSuper = true)
public class Woman extends Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "name_id")
    private WomanName name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id")
    private Settlement settlement;

    @Column(name = "pregnant_status")
    private Boolean pregnant;

    @Column(name = "pregnant_duration")
    private Integer pregnantDuration;

    @Column(name = "pregnant_recess")
    private Integer pregnantRecess;

    @Column(name = "count_baby")
    private Integer countBaby;

    @OneToOne(mappedBy = "wife", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Man husband;

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
