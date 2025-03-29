package org.fisherman.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fisherman.model.EnvironmentalObservation;
import org.fisherman.repository.EnvironmentalObservationRepository;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PredictionLinkerService {

    private final EnvironmentalObservationRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Map<LocalDateTime, Integer> predictionsByTimestamp = new HashMap<>();

    public PredictionLinkerService(EnvironmentalObservationRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void loadPredictions() {
        File file = new File("predicted-activity.json");
        if (!file.exists()) {
            System.err.println("⚠️ Prediction file not found. Skipping linkage.");
            return;
        }

        try {
            JsonNode array = objectMapper.readTree(file);
            for (JsonNode node : array) {
                LocalDateTime timestamp = LocalDateTime.parse(node.get("timestamp").asText());
                int activity = node.get("predictedActivity").asInt();
                predictionsByTimestamp.put(timestamp, activity);
            }
            System.out.println("✅ Loaded " + predictionsByTimestamp.size() + " predictions.");
        } catch (IOException e) {
            System.err.println("❌ Failed to read predictions: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<LinkedPrediction> getLinkedPredictions() {
        List<LinkedPrediction> linked = new ArrayList<>();
        try {
            Iterable<EnvironmentalObservation> all = repository.findAll();
            for (EnvironmentalObservation obs : all) {
                LocalDateTime ts = obs.getTimestamp();
                if (predictionsByTimestamp.containsKey(ts)) {
                    linked.add(new LinkedPrediction(obs, predictionsByTimestamp.get(ts)));
                }
            }
            System.out.println("✅ Linked " + linked.size() + " predictions to observations.");
        } catch (Exception e) {
            System.err.println("❌ Failed to link predictions: " + e.getMessage());
            e.printStackTrace();
        }
        return linked;
    }

    public static class LinkedPrediction {
        public EnvironmentalObservation observation;
        public int predictedActivity;

        public LinkedPrediction(EnvironmentalObservation observation, int predictedActivity) {
            this.observation = observation;
            this.predictedActivity = predictedActivity;
        }
    }
}
