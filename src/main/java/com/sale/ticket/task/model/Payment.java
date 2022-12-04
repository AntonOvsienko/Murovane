package com.sale.ticket.task.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "payment")
@EqualsAndHashCode(of = "id")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bilet_list_id")
    private Billet billet;

    @ManyToOne
    @JoinColumn(name = "payment_status_id")
    private PaymentStatus status;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronomic")
    private String patronomic;

    @Column(name = "count")
    private Integer count;
}
