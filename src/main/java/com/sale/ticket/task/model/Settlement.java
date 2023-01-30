package com.sale.ticket.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table (name = "settlement_list")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "id")
public class Settlement {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "start_time")
    private Date startTime;

    @Column (name = "last_time")
    private Date lastTime;

    @OneToMany
    @JoinColumn (name = "settlement_id")
    private List<Man> men = new ArrayList<>();

    @OneToMany
    @JoinColumn (name = "settlement_id")
    private List<Woman> women = new ArrayList<>();

}
