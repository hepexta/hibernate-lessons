package com.hepexta.lessons.hibernatelessons.data.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String emailAddress;
    private Date lastUpdateDate;
    private String lastUpdatedBy;
    private Date createdDate;
    private String createdBy;

}
