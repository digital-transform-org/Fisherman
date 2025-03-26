# 🎣 Fisherman

A Java Spring Boot web application that collects, stores, and analyzes NOAA environmental data to predict the best times for fishing Spotted Seatrout (Specks) in Galveston Bay.

---

## 🚀 Features
- Live and historical data ingestion from NOAA
- Spring Data JDBC with H2 (default), MySQL/Postgres/MSSQL ready
- Predictive model via Jupyter + Scikit-learn
- RESTful API endpoints to serve observations and predictions

---

## 🧰 Tech Stack
- Java 17 + Spring Boot 3
- Spring Data JDBC
- H2 Database (default)
- Jupyter + Python + Scikit-learn
- Maven (with mvnw wrapper)

---

## 🏁 Getting Started

### Prerequisites
- Java 17+
- Maven (`brew install maven`) OR use `./mvnw`

### Run the App
```bash
./mvnw spring-boot:run
```
Visit: `http://localhost:8080/api/observations`

### Exporting Predictions
- Run the notebook `Fisherman_Predictive_Model.ipynb`
- Save output as `predicted-activity.json` in the project root
- Access via `http://localhost:8080/api/predictions/linked`

---

## 🔮 Roadmap
- UI Dashboard (React or Thymeleaf)
- Cloud deployment (AWS, Azure, GCP)
- More species + model training
- Scraping social/bait activity

---

## 🤝 Contributing
PRs welcome! Pipeline and IaC support planned for multiple DBs.

---

## 📄 License
MIT

