package com.dejan.earthquakeproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EarthquakeDTO {

    List<Feature> features;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Feature{
        private String id;
        private Properties properties;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties{
        private Double mag;
        private String magType;
        private String place;
        private String title;
        private Long time;
    }
}
