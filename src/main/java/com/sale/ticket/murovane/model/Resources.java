//package com.sale.ticket.murovane.model;
//
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//
//import javax.persistence.*;
//
//@Data
//@Entity
//@Table (name = "resources")
//@EqualsAndHashCode(of = "id")
//public class Resources {
//
//    @Id
//    @Column(name = "id")
//    private Integer id;
//    @OneToOne(mappedBy = "resources", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Settlement settlement;
//
//    @Column (name = "food")
//    private Integer food;
//}
