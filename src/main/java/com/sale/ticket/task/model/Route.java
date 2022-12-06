package com.sale.ticket.task.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "route_list")
@EqualsAndHashCode(of = "id")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "city_output")
    private City output;

    @ManyToOne
    @JoinColumn(name = "city_input")
    private City input;

    @Column(name = "departure_time")
    private Date departureTime;

    @Column(name = "value")
    private Integer value;

    @Column(name = "count")
    private Integer count;
}
