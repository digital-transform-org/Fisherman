# 🎣 Fisherman

A predictive analytics web application that forecasts Spotted Seatrout activity in Galveston Bay based on NOAA environmental data.

Built with **Java 17**, **Spring Boot**, and **Spring Data JDBC** with support for local **H2** storage and predictive modeling via **Jupyter + Scikit-learn**.

---

## 🚀 Features

- ✅ Live and historical NOAA data integration (weather, tide, moon phase, etc.)
- ✅ Automated data persistence using Spring Data JDBC
- ✅ Hourly forecast collection + 1/5/10 year lookback
- ✅ Machine learning predictions from exported JSON
- ✅ REST API for observations and predictions
- ✅ In-memory H2 database (swappable to MySQL/Postgres/MSSQL)
- ✅ Designed for CI/CD and cloud portability (AWS, Azure, GCP)

---

## 🛠️ Prerequisites

- Java 17+
- Maven (`brew install maven`) or use the included `mvnw`
- Python 3 (for Jupyter notebook)
- Git clone of this repo

---

## 🧪 Running the App

```bash
./mvnw clean install
./mvnw spring-boot:run
