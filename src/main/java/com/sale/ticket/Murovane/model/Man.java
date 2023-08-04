package com.sale.ticket.Murovane.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Man man = (Man) o;
        return Objects.equals(id, man.id) && Objects.equals(name, man.name) && Objects.equals(settlement, man.settlement) && Objects.equals(wife, man.wife);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, settlement, wife);
    }

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
