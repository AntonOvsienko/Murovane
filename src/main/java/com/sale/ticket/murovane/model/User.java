package com.sale.ticket.murovane.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode (of = "id")
public class User{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "health")
    private Integer health;

    @Column (name = "damage")
    private Integer damage;

    @ManyToOne
    @JoinColumn (name = "profession_id")
    private Profession profession;

}
