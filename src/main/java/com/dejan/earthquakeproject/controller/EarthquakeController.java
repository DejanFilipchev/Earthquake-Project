package com.dejan.earthquakeproject.controller;

import com.dejan.earthquakeproject.exceptions.ResourceNotFoundException;
import com.dejan.earthquakeproject.model.Earthquake;
import com.dejan.earthquakeproject.repository.EarthquakeRepository;
import com.dejan.earthquakeproject.service.EarthquakeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/earthquakes")
@CrossOrigin(origins = "http://localhost:3000")
public class EarthquakeController {

    private final EarthquakeService earthquakeService;
    private final EarthquakeRepository earthquakeRepository;

    public EarthquakeController(EarthquakeService earthquakeService, EarthquakeRepository earthquakeRepository) {
        this.earthquakeService = earthquakeService;
        this.earthquakeRepository = earthquakeRepository;
    }

    @GetMapping("/fetch")
    public void EarthquakesFetch() {
        earthquakeService.fetchAndSaveAll();
    }

    @GetMapping("/after/{time}")
    public List<Earthquake> getEarthquakeAfter(@PathVariable Long time){
        return earthquakeService.getEarthquakesAfter(time);
    }

    @GetMapping("/specificmag")
    public List<Earthquake> getEarthquakesWithMag(){
        return earthquakeService.getEarthquakesWithMagGreater();
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return earthquakeRepository.findById(id)
                .map(earthquake -> {
                    earthquakeRepository.delete(earthquake);
                    return ResponseEntity.ok("Earthquake deleted");
                }).orElseThrow(() -> new ResourceNotFoundException("Earthquake not found"));
    }

    @GetMapping("/listall")
    public List<Earthquake> getAllEarthquakes(){
        return earthquakeService.getAll();
    }
}
