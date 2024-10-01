package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@WebServlet("/trmColombia")
public class TRMColombiaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("date");
        if (date != null && !date.isEmpty()) {
            try {
                // Build the API URL using the provided date
                String apiUrl = "https://trm-colombia.vercel.app/?date=" + date;

                // Fetch TRM data from the API
                JSONObject trmData = fetchTRMData(apiUrl);

                if (trmData != null && trmData.containsKey("valor")) {
                    // Store TRM data to pass it to the JSP
                    request.setAttribute("trm", trmData.get("valor"));
                    request.setAttribute("fecha", trmData.get("vigencia"));
                } else {
                    request.setAttribute("error", "No TRM data found for the date: " + date);
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "An error occurred while retrieving the TRM data.");
            }
        } else {
            request.setAttribute("error", "Date cannot be empty.");
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("/trmcolombia.jsp").forward(request, response);
    }

    private static JSONObject fetchTRMData(String apiUrl) {
        try {
            // Connect to the API
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                return null;
            }

            // Read the API response
            String jsonResponse = readApiResponse(conn);

            // Parse the JSON response
            JSONParser parser = new JSONParser();
            return (JSONObject) parser.parse(jsonResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String readApiResponse(HttpURLConnection conn) throws IOException {
        StringBuilder resultJson = new StringBuilder();
        Scanner scanner = new Scanner(conn.getInputStream());
        while (scanner.hasNext()) {
            resultJson.append(scanner.nextLine());
        }
        scanner.close();
        return resultJson.toString();
    }
}
