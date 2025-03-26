package org.fisherman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FishermanApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishermanApplication.class, args);
    }
}
