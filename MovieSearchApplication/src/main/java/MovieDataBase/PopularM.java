package MovieDataBase;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class PopularM extends HttpServlet 
{
	private static final String TMDB_API_KEY = "e4fab66da9f615c07892ead6b42a792f"; 
    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + TMDB_API_KEY;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String apiUrl = TMDB_API_BASE_URL;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) { 
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // Parse JSON response
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(content.toString(), JsonObject.class);
            JsonArray results = jsonResponse.getAsJsonArray("results");

            // Start building the HTML response
            StringBuilder htmlResponse = new StringBuilder();
            htmlResponse.append("<!DOCTYPE html>");
            htmlResponse.append("<html lang='en'>");
            htmlResponse.append("<head>");
            htmlResponse.append("<meta charset='UTF-8'>");
            htmlResponse.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            htmlResponse.append("<title>Popular Movies</title>");
            htmlResponse.append("<link rel='stylesheet' type='text/css' href='scrolling.css'>");
            htmlResponse.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css\" integrity=\"sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />");
            htmlResponse.append("</head>");
            htmlResponse.append("<body>");
            htmlResponse.append("<div id='movie-container' class='movie-container'>");
          
            String id = "";
            for (int i = 0; i < results.size(); i++) {
                JsonObject movie = results.get(i).getAsJsonObject();
                
                 id = movie.get("id").getAsString();
                System.out.println(id);
                 
                String title = escapeHtml(getJsonString(movie, "title"));
                System.out.println(title);
                String overview = escapeHtml(getJsonString(movie, "overview"));
                String releaseDate = escapeHtml(getJsonString(movie, "release_date"));
                String posterPath = getJsonString(movie, "poster_path");

                htmlResponse.append("<div class='card'>");
                String contextPath = request.getContextPath();
                htmlResponse.append("<a href='").append(contextPath).append("/movieDetails?id=").append(id).append("-").append(title).append("' target='_top'>");


                if (!posterPath.isEmpty()) {
                    htmlResponse.append("<div class='img-container'>");
                    htmlResponse.append("<img src='https://image.tmdb.org/t/p/w500").append(posterPath).append("' alt='").append(title).append("' style='width:100%; height:100%;'>");
                    htmlResponse.append("</div>");
                }

                htmlResponse.append("</a>");
               // htmlResponse.append("<div class='scroll-container'>");
                
                htmlResponse.append( "<div class='scroll-wrapper'>");
                htmlResponse.append( "<div class='scroll-content'>");
                htmlResponse.append( "<div class='content'>");
                
                    
                
                
                htmlResponse.append("<div class='card-body'>");
                htmlResponse.append("<div class='card-title'>").append(title).append("</div>");
                htmlResponse.append("</div>");
                htmlResponse.append("</div>");
                htmlResponse.append("</div>");
                htmlResponse.append("</div>");
                htmlResponse.append("</div>");
            }

            htmlResponse.append("</div>");
            htmlResponse.append("<script>");
            htmlResponse.append("function getDetails(event, id) {");
            htmlResponse.append(" event.preventDefault(); ");
            htmlResponse.append("window.location.href = 'https://api.themoviedb.org/3/movie/" + id + "?api_key=" + TMDB_API_KEY + "';");
            htmlResponse.append("}");

            
            htmlResponse.append("</script>");
            
            htmlResponse.append("</body></html>");


            // Send the HTML response back to the client
            response.setContentType("text/html");
            response.getWriter().write(htmlResponse.toString());

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Failed to fetch data from TMDB API");
        }
        connection.disconnect();
    }

    // Helper method to safely get a String from a JsonObject
    private String getJsonString(JsonObject jsonObject, String memberName) {
        JsonElement jsonElement = jsonObject.get(memberName);
        return jsonElement != null && !jsonElement.isJsonNull() ? jsonElement.getAsString() : "";
    }

    // Helper method to escape HTML characters
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }

}
