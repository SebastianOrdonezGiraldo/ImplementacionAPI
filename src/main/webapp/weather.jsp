<%@ page import="org.json.simple.JSONObject" %>
<!-- File: weather.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Weather Information</title>
</head>
<body>
<h1>Enter a City to Get Weather Information</h1>
<form method="get" action="weather">
    <label for="city">City:</label>
    <input type="text" id="city" name="city" required>
    <button type="submit">Get Weather</button>
</form>

<hr>

<%
    if (request.getAttribute("weatherData") != null) {
        JSONObject weatherData = (JSONObject) request.getAttribute("weatherData");
        JSONObject currentWeather = (JSONObject) weatherData.get("current_weather");
        if (currentWeather != null) {
%>
<h2>Weather Details:</h2>
<p>Temperature: <%= currentWeather.get("temperature") %> °C</p>
<p>Wind Speed: <%= currentWeather.get("windspeed") %> km/h</p>
<p>Time: <%= currentWeather.get("time") %></p>
<%
} else {
%>
<p>No weather data available.</p>
<%
    }
} else if (request.getAttribute("error") != null) {
%>
<p>Error: <%= request.getAttribute("error") %></p>
<%
    }
%>

</body>
</html>
