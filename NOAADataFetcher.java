package org.fisherman.data.noaa;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NOAADataFetcher {

    private static final String WEATHER_GOV_BASE = "https://api.weather.gov";
    private static final String COOPS_BASE = "https://api.tidesandcurrents.noaa.gov/api/prod/datagetter";
    private static final String GALVESTON_STATION = "8771341"; // Galveston Pier 21

    private final HttpClient client;
    private final ObjectMapper mapper;

    public NOAADataFetcher() {
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public void fetchLiveForecast() throws IOException, InterruptedException {
        System.out.println("🌤️ Fetching live forecast data...");

        String pointsUrl = WEATHER_GOV_BASE + "/points/29.3013,-94.7977";
        HttpRequest pointsRequest = HttpRequest.newBuilder()
                .uri(URI.create(pointsUrl))
                .header("User-Agent", "FishermanApp (digital-transform.org)")
                .build();

        HttpResponse<String> pointsResponse = client.send(pointsRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode pointsJson = mapper.readTree(pointsResponse.body());
        String forecastUrl = pointsJson.at("/properties/forecast").asText();

        HttpRequest forecastRequest = HttpRequest.newBuilder()
                .uri(URI.create(forecastUrl))
                .header("User-Agent", "FishermanApp (digital-transform.org)")
                .build();

        HttpResponse<String> forecastResponse = client.send(forecastRequest, HttpResponse.BodyHandlers.ofString());
        JsonNode forecastJson = mapper.readTree(forecastResponse.body());

        for (JsonNode period : forecastJson.at("/properties/periods")) {
            System.out.printf("📅 %s: %s°F, Wind: %s, %s%n",
                    period.get("name").asText(),
                    period.get("temperature").asText(),
                    period.get("windSpeed").asText(),
                    period.get("detailedForecast").asText());
        }
    }

    public void fetchHistoricalData() throws IOException, InterruptedException {
        System.out.println("\n📚 Fetching historical data...");

        int[] yearsAgo = {1, 5, 10};
        for (int years : yearsAgo) {
            LocalDate date = LocalDate.now().minusYears(years);
            String dateStr = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            fetchCOOPSData("water_temperature", dateStr, years);
            fetchCOOPSData("air_temperature", dateStr, years);
        }
    }

    private void fetchCOOPSData(String product, String date, int yearsAgo) throws IOException, InterruptedException {
        String url = String.format("%s?begin_date=%s&end_date=%s&station=%s&product=%s&datum=MLLW&units=english&time_zone=lst_ldt&format=json",
                COOPS_BASE, date, date, GALVESTON_STATION, product);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "FishermanApp (digital-transform.org)")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonNode json = mapper.readTree(response.body());

        System.out.printf("🕰️ %d years ago (%s) - %s:%n", yearsAgo, date, product);
        for (JsonNode entry : json.path("data")) {
            String timestamp = entry.get("t").asText();
            String value = entry.get("v").asText();
            System.out.printf("  📍 %s: %s %s%n", timestamp, value, product.equals("air_temperature") ? "°F" : "°F");
        }
    }

    public static void main(String[] args) {
        NOAADataFetcher fetcher = new NOAADataFetcher();
        try {
            fetcher.fetchLiveForecast();
            fetcher.fetchHistoricalData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

