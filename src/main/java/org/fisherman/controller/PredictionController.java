package org.fisherman.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fisherman.service.PredictionLinkerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PredictionLinkerService predictionLinkerService;

    public PredictionController(PredictionLinkerService predictionLinkerService) {
        this.predictionLinkerService = predictionLinkerService;
    }

    @GetMapping
    public List<JsonNode> getPredictions() throws IOException {
        File predictionFile = new File("predicted-activity.json");
        if (!predictionFile.exists()) {
            throw new IOException("Prediction file not found.");
        }
        return objectMapper.readValue(predictionFile, objectMapper.getTypeFactory().constructCollectionType(List.class, JsonNode.class));
    }

    @GetMapping("/linked")
    public List<PredictionLinkerService.LinkedPrediction> getLinkedPredictions() {
        return predictionLinkerService.getLinkedPredictions();
    }
}