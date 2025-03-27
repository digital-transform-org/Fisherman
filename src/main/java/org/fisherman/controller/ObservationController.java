package org.fisherman.controller;

import org.fisherman.model.EnvironmentalObservation;
import org.fisherman.repository.EnvironmentalObservationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
public class ObservationController {

    private final EnvironmentalObservationRepository repository;

    public ObservationController(EnvironmentalObservationRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<EnvironmentalObservation> getAllObservations() {
        return (List<EnvironmentalObservation>) repository.findAll();
    }
}

