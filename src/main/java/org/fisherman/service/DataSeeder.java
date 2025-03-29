package org.fisherman.service;

import org.fisherman.model.EnvironmentalObservation;
import org.fisherman.repository.EnvironmentalObservationRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSeeder {

    private final EnvironmentalObservationRepository repository;

    public DataSeeder(EnvironmentalObservationRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void seedDataIfEmpty() {
        if (repository.count() == 0) {
            List<EnvironmentalObservation> sampleData = List.of(
                new EnvironmentalObservation(null, LocalDateTime.parse("2024-03-26T04:00:00"), 68.5, 70.2, 1015.3, 1.2, 10.1, "SE", 40.0, 0.0, "Waning Gibbous"),
                new EnvironmentalObservation(null, LocalDateTime.parse("2024-03-26T05:00:00"), 67.8, 69.9, 1014.8, 1.3, 9.5, "E", 35.0, 0.0, "Waning Gibbous")
            );
            repository.saveAll(sampleData);
            System.out.println("✅ Seeded sample environmental observations.");
        } else {
            System.out.println("ℹ️ Observations already present — skipping seeding.");
        }
    }
}

