package servlets;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@WebServlet("/rickandmorty")
public class RickAndMortyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String characterName = request.getParameter("characterName");
        if (characterName != null && !characterName.isEmpty()) {
            try {
                // Replace spaces in the character name with '+'
                characterName = characterName.replaceAll(" ", "+");

                // Build the API URL for searching character by name
                String apiUrl = "https://rickandmortyapi.com/api/character/?name=" + characterName;

                // Fetch character data from API
                JSONObject characterData = fetchCharacterData(apiUrl);

                if (characterData != null && characterData.containsKey("results")) {
                    // Get the first result from the array
                    JSONArray resultsArray = (JSONArray) characterData.get("results");
                    if (!resultsArray.isEmpty()) {
                        JSONObject character = (JSONObject) resultsArray.get(0);
                        request.setAttribute("character", character);
                    } else {
                        request.setAttribute("error", "No character found with the name: " + characterName);
                    }
                } else {
                    request.setAttribute("error", "No character found with the name: " + characterName);
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "An error occurred while retrieving the character data.");
            }
        } else {
            request.setAttribute("error", "Character name cannot be empty.");
        }

        // Forward the request to the JSP page
        request.getRequestDispatcher("/rickandmorty.jsp").forward(request, response);
    }

    private static JSONObject fetchCharacterData(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() != 200) {
                return null;
            }

            // Read the API response
            String jsonResponse = readApiResponse(conn);

            // Parse the response as JSON
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
