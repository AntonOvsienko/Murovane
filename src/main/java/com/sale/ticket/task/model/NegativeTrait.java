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
@Table (name = "negative_traits")
@EqualsAndHashCode (of = "id")
public class NegativeTrait {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "trait")
    private String trait;

    @Column (name = "description")
    private String description;

}
