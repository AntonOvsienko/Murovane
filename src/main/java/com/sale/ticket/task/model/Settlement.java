package com.sale.ticket.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
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

    @Column (name = "start_time")
    private Date startTime;

    @Column (name = "last_time")
    private Date lastTime;

    @Cascade (value = {org.hibernate.annotations.CascadeType.ALL})
    @OneToMany (mappedBy = "settlement", fetch = FetchType.LAZY)
    private List<Man> men = new ArrayList<>();

    @Cascade (value = {org.hibernate.annotations.CascadeType.ALL})
    @OneToMany (mappedBy = "settlement", fetch = FetchType.LAZY)
    private List<Woman> women = new ArrayList<>();

}
