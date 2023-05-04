package com.sale.ticket.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "man_list")
@EqualsAndHashCode (of = "id")
public class Man extends Individual {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "name_id")
    private ManName name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "settlement_id")
    private Settlement settlement;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wife_id")
    private Woman wife;


//    @ManyToOne
//    @JoinColumn (name = "father_id")
//    private Man myFather;
//
//    @OneToMany (mappedBy = "myFather", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Man> sons;
//
//    @ManyToOne
//    @JoinColumn (name = "mother_id")
//    private Woman mother;
//
//    @OneToMany (mappedBy = "mother", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Woman> daughters;

    //    @ManyToMany
    //    @JoinTable (name = "man_negative",
    //            joinColumns = {@JoinColumn (name = "man_list")},
    //            inverseJoinColumns = {@JoinColumn(name = "negative_traits")})
    //    private List<NegativeTrait> individualNegative = new ArrayList<>();
    //
    //    @ManyToMany
    //    @JoinTable (name = "man_positive",
    //            joinColumns = {@JoinColumn(name = "man_list")},
    //            inverseJoinColumns = {@JoinColumn(name = "positive_traits")})
    //    private List<PositiveTrait> individualPositive = new ArrayList<>();
}
