package org.fisherman.model;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;

public class EnvironmentalObservation {

    @Id
    private Long id;
    private LocalDateTime timestamp;
    private Double airTemperature;
    private Double waterTemperature;
    private Double airPressure;
    private Double waterLevel;
    private Double windSpeed;
    private String windDirection;
    private Double cloudCover;
    private Double precipitation;
    private String moonPhase;

    public EnvironmentalObservation(
    Long id,
    LocalDateTime timestamp,
    Double airTemperature,
    Double waterTemperature,
    Double airPressure,
    Double waterLevel,
    Double windSpeed,
    String windDirection,
    Double cloudCover,
    Double precipitation,
    String moonPhase
) {
    this.id = id;
    this.timestamp = timestamp;
    this.airTemperature = airTemperature;
    this.waterTemperature = waterTemperature;
    this.airPressure = airPressure;
    this.waterLevel = waterLevel;
    this.windSpeed = windSpeed;
    this.windDirection = windDirection;
    this.cloudCover = cloudCover;
    this.precipitation = precipitation;
    this.moonPhase = moonPhase;
}

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(Double airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Double getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Double waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public Double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Double airPressure) {
        this.airPressure = airPressure;
    }

    public Double getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(Double waterLevel) {
        this.waterLevel = waterLevel;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public String getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(String moonPhase) {
        this.moonPhase = moonPhase;
    }
}
