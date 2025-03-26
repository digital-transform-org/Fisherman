package org.fisherman.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fisherman.model.EnvironmentalObservation;
import org.fisherman.repository.EnvironmentalObservationRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class ObservationExporter {

    private final EnvironmentalObservationRepository repository;
    private final ObjectMapper objectMapper;

    public ObservationExporter(EnvironmentalObservationRepository repository) {
        this.repository = repository;
        this.objectMapper = new ObjectMapper();
    }

    @PostConstruct
    public void exportObservationsToJson() {
        try {
            List<EnvironmentalObservation> observations = (List<EnvironmentalObservation>) repository.findAll();
            File outputFile = new File("observations-export.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, observations);
            System.out.println("✅ Exported " + observations.size() + " observations to observations-export.json");
        } catch (IOException e) {
            System.err.println("❌ Failed to export observations: " + e.getMessage());
        }
    }
}