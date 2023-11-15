package com.sale.ticket.murovane.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "woman_list")
@EqualsAndHashCode(callSuper = true)
public class Woman extends Individual {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settlement_id")
    @JsonBackReference
    private Settlement settlement;

    @Column(name = "pregnant_status")
    private Boolean pregnant;

    @Column(name = "pregnant_duration")
    private Integer pregnantDuration;

    @Column(name = "pregnant_recess")
    private Integer pregnantRecess;

    @Column(name = "count_baby")
    private Integer countBaby;

    @OneToOne(mappedBy = "wife", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JsonBackReference(value = "manReference")
    private Man husband;
}
