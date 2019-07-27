package com.hepexta.lessons.hibernatelessons.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "finances_keys")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Access(value = AccessType.FIELD)
public class FinancesKeys {
    @Id
    @Column(name = "TABLE_NAME")
    private String tableName;
    @Column(name = "VALUE", nullable = false)
    private Integer value;
}
