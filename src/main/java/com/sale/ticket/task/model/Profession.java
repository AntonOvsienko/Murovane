package com.sale.ticket.task.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table (name = "profession_list")
@EqualsAndHashCode (of = "id")
public class Profession {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "class")
    private String className;

    @Column (name = "strength")
    private Integer strength;

    @Column (name = "dexterity")
    private Integer dexterity;

    @Column (name = "stamina")
    private Integer stamina;

    @Column (name = "magic")
    private Integer magic;

}
