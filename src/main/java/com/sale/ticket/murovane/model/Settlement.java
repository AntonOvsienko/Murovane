package com.sale.ticket.murovane.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "settlement_list")
@EqualsAndHashCode (of = "id")
public class Settlement {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (name = "name")
    private String name;

    @Column (name = "last_time")
    private LocalDateTime lastTime;

    @Column (name = "settlement_time")
    private LocalDate settlementTime;

    @OneToMany (mappedBy = "settlement", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Man> men = new ArrayList<>();

    @OneToMany (mappedBy = "settlement", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Woman> women = new ArrayList<>();

}
