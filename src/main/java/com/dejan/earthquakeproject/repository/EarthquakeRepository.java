package com.dejan.earthquakeproject.repository;

import com.dejan.earthquakeproject.model.Earthquake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EarthquakeRepository extends JpaRepository<Earthquake,String> {

    List<Earthquake> findByTimeGreaterThan(Long time);
    List<Earthquake> findByMagGreaterThan(Double mag);
}
