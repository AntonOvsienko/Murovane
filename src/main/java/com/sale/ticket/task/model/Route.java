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
    private Long id;

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

    public Route(City output, City input, Date departureTime, Integer value, Integer count) {
        this.output = output;
        this.input = input;
        this.departureTime = departureTime;
        this.value = value;
        this.count = count;
    }
}
