package com.dejan.earthquakeproject.service.impl;

import com.dejan.earthquakeproject.model.Earthquake;
import com.dejan.earthquakeproject.model.EarthquakeDTO;
import com.dejan.earthquakeproject.repository.EarthquakeRepository;
import com.dejan.earthquakeproject.service.EarthquakeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EarthquakeServiceImpl implements EarthquakeService {

    private final EarthquakeRepository earthquakeRepository;

    public EarthquakeServiceImpl(EarthquakeRepository earthquakeRepository) {
        this.earthquakeRepository = earthquakeRepository;
    }

    @Value("${earthquake.url}")
    private String apiUrl;

    @Override
    @Transactional
    public void fetchAndSaveAll() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            EarthquakeDTO response = restTemplate.getForObject(apiUrl, EarthquakeDTO.class);

            if(response != null && response.getFeatures() != null){
                List<Earthquake> listall = response.getFeatures().stream()
                        .map(feature -> new Earthquake(
                                feature.getId(),
                                feature.getProperties().getMag(),
                                feature.getProperties().getMagType(),
                                feature.getProperties().getPlace(),
                                feature.getProperties().getTitle(),
                                feature.getProperties().getTime()
                        ))
                        .collect(Collectors.toList());

                earthquakeRepository.deleteAll();
                earthquakeRepository.saveAll(listall);
            }
        }catch (Exception e){

            System.out.println("Error fetching all data " + e.getMessage());

        }

    }

    @Override
    public List<Earthquake> getEarthquakesAfter(Long time) {
        return earthquakeRepository.findByTimeGreaterThan(time);
    }

    @Override
    public List<Earthquake> getEarthquakesWithMagGreater() {
        return earthquakeRepository.findByMagGreaterThan(2.0);
    }

    @Override
    public void deleteById(String id) {
        if(earthquakeRepository.existsById(id)){
            earthquakeRepository.deleteById(id);
        }else {
            System.out.println("No such earthquake with id " + id);
        }
    }

    @Override
    public List<Earthquake> getAll(){
        return earthquakeRepository.findAll();
    }
}
