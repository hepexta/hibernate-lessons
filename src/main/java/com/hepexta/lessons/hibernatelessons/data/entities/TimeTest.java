package com.hepexta.lessons.hibernatelessons.data.entities;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "time_test")
public class TimeTest {

    @Id
    @GeneratedValue
    private Long timeTestId;
    private Date datetime;
    private Date timestamp;
    private Date date;
    private Date time;
    private java.sql.Timestamp sqlDatetime;
    private java.sql.Timestamp sqlTimestamp;
    private java.sql.Date sqlDate;
    private java.sql.Time sqlTime;

    public TimeTest(Date date){
        this.datetime = date;
        this.timestamp = date;
        this.time = date;
        this.date = date;

        this.sqlDatetime = new java.sql.Timestamp(date.getTime());
        this.sqlTimestamp = new java.sql.Timestamp(date.getTime());
        this.sqlDate = new java.sql.Date(date.getTime());
        this.sqlTime = new java.sql.Time(date.getTime());
    }
}
