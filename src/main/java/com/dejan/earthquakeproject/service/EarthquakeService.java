package com.dejan.earthquakeproject.service;

import com.dejan.earthquakeproject.model.Earthquake;

import java.util.List;

public interface EarthquakeService {

    void fetchAndSaveAll();
    List<Earthquake> getEarthquakesAfter(Long time);
    List<Earthquake> getEarthquakesWithMagGreater();
    void deleteById(String id);
    List<Earthquake> getAll();
}


