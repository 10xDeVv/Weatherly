# 🌤️ WeatherApp

**WeatherApp** is a real-time Java-based weather dashboard built with **JavaFX**, utilizing the **Java HTTP Client** for real-time weather data retrieval from external APIs and **Jackson** for JSON parsing. Designed with an intuitive UI and clean MVC architecture, the app displays current, hourly, and daily forecasts with real-time updates.

<img src="src/main/resources/org/example/weatherapp/screenshots/img.png">

---

## 📌 Features

- 🔍 **City Search**: Enter any city name to get live weather updates.
- 🌡️ **Current Weather**: Displays temperature, UV index, pressure, dew point, visibility, wind speed, and “feels like” temperature.
- 📅 **Forecasts**: Hourly and daily weather forecasts presented in a user-friendly interface.
- 🌐 **Live Data**: Retrieves real-time data using an external weather API.
- 🖼️ **Dynamic Icons**: Weather conditions are mapped to context-aware icons using enums.
- 💡 **Lombok Integration**: Simplifies model class definitions with annotations.

---

## 🧩 Main Components

- **`module-info.java`**: Defines modular structure using the Java Module System.
- **`App.java`**: Launches the JavaFX application.
- **Models**:
    - `WeatherData`: Core weather model with all relevant attributes.
    - `DailyForecast`: Daily forecast data.
    - `HourlyForecast`: Hourly forecast data.
    - `Icon`: Simple model mapping weather labels to icon image paths.
    - `WeatherCondition`: Enum that maps descriptive conditions to icon resources based on time of day.
- **Controllers**: Manage JavaFX UI behavior and user interaction logic.
- **Service Layer**: Connects API client to data parsing logic and app logic.

---

## 🛠️ Tech Stack & Dependencies

| Component         | Purpose                                           |
|------------------|---------------------------------------------------|
| JavaFX           | UI rendering and interaction                      |
| Java HTTP Client | Making HTTP requests to weather APIs              |
| Jackson          | JSON parsing (`jackson-core`, `jackson-databind`) |
| Lombok           | Reduces boilerplate in model classes              |

---

## ⚙️ Build & Configuration

This project uses **Maven** for build automation and dependency management.

- **Java Version**: 23+
- **Build Tool**: Maven with `javafx-maven-plugin`
- **Compiler Plugin**: Configured for Java 23
- **Project Configuration**: Managed in `pom.xml`

---

### 📦 Build and Run:

```bash
# Clone the repository
git clone https://github.com/your-username/weatherapp.git
cd weatherapp

# Build the project
mvn clean install

# Run the JavaFX app
mvn javafx:run

org.example.weatherapp/
├── App.java                          # JavaFX application entry point
├── module-info.java                  # Java module definition
├── controllers/                      # JavaFX controllers
├── models/                           # Weather data models
├── service/                          # Weather API service and logic

weatherly/
├── App.java                         # JavaFX application entry point
├── module-info.java                 # Java module definition
├── client/
│   └── WeatherApiClient.java        # Handles API calls via HTTPClient
├── service/
│   └── ApiService.java              # Connects client and parser
├── utility/
│   ├── WeatherParser.java           # Parses JSON responses
│   ├── DateFormatter.java           # Formats date & time display
│   └── IconUtility.java             # Selects weather icons (day/night)
├── models/
│   ├── WeatherData.java             # Aggregates weather information
│   ├── DailyForecast.java
│   ├── HourlyForecast.java
│   ├── Icon.java
│   └── WeatherCondition.java
├── controllers/
│   ├── MainWeatherController.java
│   ├── DailyForecastController.java
│   └── HourlyForecastController.java
├── fxml/
│   └── MainWeatherView.fxml         # Declarative UI layout
├── icons/                           # Weather icons (big, medium, small)
├── screenshots/                     # App screenshots (optional)
└── README.md

📘 Notable Model: WeatherData
The WeatherData class aggregates all weather-related information, including:

📍 Location: City, country, local time

☀️ Current Conditions: Temperature, pressure, wind speed, visibility

🌅 Astronomy: Sunrise, sunset, moonrise, moonset

📊 Forecasts: Hourly and daily weather breakdowns

🔐 API Configuration
To use this app, configure your weather API key (e.g., from OpenWeather or WeatherAPI) in the appropriate service class. Make sure to keep API credentials secure.

🧑‍💻 Development Environment
Java SDK 23+

Maven 3.8+

IDE with JavaFX support (e.g., IntelliJ IDEA, Eclipse)

weatherly/
├── App.java # JavaFX application launcher
├── client/
│ └── WeatherApiClient.java # Handles API calls via HTTPClient
├── service/
│ └── ApiService.java # Connects client and parser
├── utility/
│ ├── WeatherParser.java # Parses JSON responses
│ ├── DateFormatter.java # Handles date & time formatting
│ └── IconUtility.java # Selects appropriate weather icons
├── models/ # Java POJOs for weather data
├── controllers/
│ ├── MainWeatherController.java
│ ├── DailyForecastController.java
│ └── HourlyForecastController.java
├── fxml/
│ └── MainWeatherView.fxml # Main UI layout
├── icons/ # Weather icons (big, medium, small)
├── screenshots/ # App screenshots (optional)
└── README.md

---

## ⚙️ Setup Instructions

### 1. 🔑 Get Your API Key
Sign up and subscribe to [WeatherAPI on RapidAPI](https://rapidapi.com/weatherapi/api/weatherapi-com/) to get your `X-RapidAPI-Key`.

---

### 2. 💻 Set Environment Variable

**Windows (CMD):**
```cmd
set API_KEY=your_rapidapi_key_here
