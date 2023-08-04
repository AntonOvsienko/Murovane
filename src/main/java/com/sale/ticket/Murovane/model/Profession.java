package com.sale.ticket.Murovane.model;

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

    @Column (name = "classname")
    private String className;

//    @Column (name = "")
//    private boolean isMan;

}
