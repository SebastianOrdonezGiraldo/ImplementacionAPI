package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String city = request.getParameter("city");
        if (city != null && !city.isEmpty()) {
            try {
                // Get location data
                JSONObject cityLocationData = getLocationData(city);
                if (cityLocationData != null) {
                    double latitude = (double) cityLocationData.get("latitude");
                    double longitude = (double) cityLocationData.get("longitude");

                    // Display weather data
                    JSONObject weatherData = getWeatherData(latitude, longitude);
                    request.setAttribute("weatherData", weatherData);
                } else {
                    request.setAttribute("error", "Could not retrieve location data.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "An error occurred while retrieving the weather data.");
            }
        }

        // Forward to the JSP page
        request.getRequestDispatcher("/weather.jsp").forward(request, response);
    }

    private static JSONObject getLocationData(String city) {
        city = city.replaceAll(" ", "+");
        String urlString = "https://geocoding-api.open-meteo.com/v1/search?name=" +
                city + "&count=1&language=en&format=json";

        try {
            HttpURLConnection apiConnection = fetchApiResponse(urlString);
            if (apiConnection.getResponseCode() != 200) {
                return null;
            }
            String jsonResponse = readApiResponse(apiConnection);
            JSONParser parser = new JSONParser();
            JSONObject resultsJsonObj = (JSONObject) parser.parse(jsonResponse);
            JSONArray locationData = (JSONArray) resultsJsonObj.get("results");
            return (JSONObject) locationData.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject getWeatherData(double latitude, double longitude) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude +
                "&longitude=" + longitude + "&current_weather=true";

        try {
            HttpURLConnection apiConnection = fetchApiResponse(url);
            if (apiConnection.getResponseCode() != 200) {
                return null;
            }
            String jsonResponse = readApiResponse(apiConnection);
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readApiResponse(HttpURLConnection apiConnection) throws IOException {
        StringBuilder resultJson = new StringBuilder();
        Scanner scanner = new Scanner(apiConnection.getInputStream());
        while (scanner.hasNext()) {
            resultJson.append(scanner.nextLine());
        }
        scanner.close();
        return resultJson.toString();
    }

    private static HttpURLConnection fetchApiResponse(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return conn;
    }
}
