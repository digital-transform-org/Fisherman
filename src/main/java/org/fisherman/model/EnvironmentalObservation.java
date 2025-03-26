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

    // Getters and setters omitted for brevity
 public LocalDateTime getTimestamp() {
    return timestamp;
}
}
