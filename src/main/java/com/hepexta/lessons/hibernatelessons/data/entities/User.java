package com.hepexta.lessons.hibernatelessons.data.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Access(value = AccessType.FIELD)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "user_table_generator")
    @TableGenerator(name = "user_table_generator", table = "finances_keys", pkColumnName = "TABLE_NAME", pkColumnValue = "user", valueColumnName = "VALUE")
    @Column(name = "userId")
    private Long id;
    private String firstName;
    private String lastName;
    @Column(nullable = false)
    private Date birthDate;
    private String emailAddress;
    private Date lastUpdateDate;
    private String lastUpdatedBy;
    @Column(updatable = false)
    private Date createdDate;
    @Column(updatable = false)
    private String createdBy;

}
