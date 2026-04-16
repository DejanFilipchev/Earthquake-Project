package com.dejan.earthquakeproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "earthquakes")
@Data
public class Earthquake {

    @Id
    private String id;

    private Double mag;
    private String magType;
    private String place;
    private String title;
    private Long time;

    public Earthquake() {
    }

    public Earthquake(String id, Double mag, String magType, String place, String title, Long time) {
        this.id = id;
        this.mag = mag;
        this.magType = magType;
        this.place = place;
        this.title = title;
        this.time = time;
    }
}
