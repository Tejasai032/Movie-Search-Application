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

public class TopRatedTv extends HttpServlet 
{
	private static final String TMDB_API_KEY = "e4fab66da9f615c07892ead6b42a792f"; 
    private static final String TMDB_API_BASE_URL = "https://api.themoviedb.org/3/tv/top_rated?api_key=" + TMDB_API_KEY;

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
            htmlResponse.append("<link rel='stylesheet' type='text/css' href='Nowplaying.css'>");
            htmlResponse.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css\" integrity=\"sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />");
            htmlResponse.append("<style>");
            htmlResponse.append("body {");
            htmlResponse.append("    font-family: Poppins, sans-serif;");
            htmlResponse.append("}");
            htmlResponse.append("#search {");
            htmlResponse.append("    width: 70%;");
            htmlResponse.append("    height: 40px;");
            htmlResponse.append("    border-radius: 18px;");
            htmlResponse.append("    border: none;");
            htmlResponse.append("    position:relative;");
            htmlResponse.append("    top: 30%;");
            htmlResponse.append("}");
            htmlResponse.append("#bsearch {");
            htmlResponse.append("    width: 10%;");
            htmlResponse.append("    height: 40px;");
            htmlResponse.append("    border-radius: 18px;");
            htmlResponse.append("    cursor: pointer;");
            htmlResponse.append("    position: relative;");
            htmlResponse.append("    right: 10.9%;");
            htmlResponse.append("    border: none;");
            htmlResponse.append("    position:relative;");
            htmlResponse.append("    top: 30%;");
            htmlResponse.append("}");
            htmlResponse.append("header {");
            htmlResponse.append("    display: flex;");
            htmlResponse.append("    justify-content: space-around;");
            htmlResponse.append("}");
            htmlResponse.append("#icon {");
            htmlResponse.append("    position: relative;");
            htmlResponse.append("}");
            htmlResponse.append("a {");
            htmlResponse.append("    text-decoration: none;");
            htmlResponse.append("    color: white;");
            htmlResponse.append("}");
            htmlResponse.append(".main {");
            htmlResponse.append("    background-color: rgb(60, 61, 55);");
            htmlResponse.append("    padding-bottom: 2%;");
            htmlResponse.append("    padding-top: 1%;");
            htmlResponse.append("}");
            htmlResponse.append(".mgenre, .Tvgenre {");
            htmlResponse.append("    display: none;");
            htmlResponse.append("    flex-direction: column;");
            htmlResponse.append("    justify-content: space-around;");
            htmlResponse.append("    border: 2px solid white;");
            htmlResponse.append("    border-radius: 8px;");
            htmlResponse.append("    font-size: 12px;");
            htmlResponse.append("    position: absolute;");
            htmlResponse.append("    background-color: rgb(60, 61, 55);");
            htmlResponse.append("    padding: 10px;");
            htmlResponse.append("}");
            htmlResponse.append(".mgenre {");
            htmlResponse.append("    width: 12%;");
            htmlResponse.append("    left: 23%;");
            htmlResponse.append("    top: 10%;");
            htmlResponse.append("}");
            htmlResponse.append(".Tvgenre {");
            htmlResponse.append("    width: 10%;");
            htmlResponse.append("    left: 45%;");
            htmlResponse.append("    top: 10%;");
            htmlResponse.append("}");
            htmlResponse.append(".open-popup {");
            htmlResponse.append("    display: flex;");
            htmlResponse.append("}");
            htmlResponse.append(".open-popup1 {");
            htmlResponse.append("    display: flex;");
            htmlResponse.append("}");
            htmlResponse.append(".navbarcontainer {");
            htmlResponse.append("    width: 50%;");
            htmlResponse.append("    display: flex;");
            htmlResponse.append("    justify-content: space-between;");
            htmlResponse.append("}");
            htmlResponse.append("form {");
            htmlResponse.append("    width: 30%;");
            htmlResponse.append("}");
            htmlResponse.append(".view {");
            htmlResponse.append("    display: inline;");
            htmlResponse.append("}");
            
            htmlResponse.append(".home {");
            htmlResponse.append("width:100%;");
            htmlResponse.append("height:50px;");
            htmlResponse.append("}");
            htmlResponse.append("</style>");
            htmlResponse.append("</head>");
            htmlResponse.append("<body>");
            htmlResponse.append("<div class=\"main\">");

            htmlResponse.append("<header>");
            htmlResponse.append("<div class=\"navbarcontainer\">");
            htmlResponse.append("<div id=\"icon\">");
            htmlResponse.append("<h2><a href=\"MoviePage.jsp\"><img src=\"https://r2.erweima.ai/ideogram/L3rDFPRHRHeyOVVEJA7p7w.jpg\" class=\"home\"></a></h2>");
            htmlResponse.append("</div>");
            htmlResponse.append("<div>");
            htmlResponse.append("<h2><a href=\"#\" onClick=\"openPopup()\">Movies</a></h2>");
            htmlResponse.append("</div>");
            htmlResponse.append("<div>");
            htmlResponse.append("<h2><a href=\"#\" onClick=\"openPopup1()\">Web Series</a></h2>");
            htmlResponse.append("</div>");
            htmlResponse.append("</div>");
            htmlResponse.append("<form action=\"TmdbApiCall\" method=\"get\">");
            htmlResponse.append("<input type=\"search\" id=\"search\" name=\"query\">");
            htmlResponse.append("<button type=\"submit\" id=\"bsearch\">");
            htmlResponse.append("<i class=\"fa-sharp-duotone fa-solid fa-magnifying-glass\"></i>");
            htmlResponse.append("</button>");
            htmlResponse.append("</form>");
            htmlResponse.append("</header>");

            htmlResponse.append("<div class=\"mgenre\" id=\"show\">");
            htmlResponse.append("<h2><a href=\"NowPlayingM\">Now Playing</a></h2>");
            htmlResponse.append("<h2><a href=\"TreandingMovies\">Trending Movies</a></h2>");
            htmlResponse.append("<h2><a href=\"UpComingM\">Upcoming</a></h2>");
            htmlResponse.append("</div>");

            htmlResponse.append("<div class=\"Tvgenre\" id=\"display\">");
            htmlResponse.append("<h2><a href=\"PopularTv\">Popular</a></h2>");
            htmlResponse.append("<h2><a href=\"TopRatedTv\">Top Rated</a></h2>");
            htmlResponse.append("<h2><a href=\"OnTheAirTv\">On The Air</a></h2>");
            htmlResponse.append("</div>");

            htmlResponse.append("</div>");
            htmlResponse.append("<script>");
            htmlResponse.append("function openPopup() {");
            htmlResponse.append("    let popup = document.getElementById('show');");
            htmlResponse.append("    popup.classList.toggle('open-popup');");
            htmlResponse.append("}");
            htmlResponse.append("function openPopup1() {");
            htmlResponse.append("    let popupDisplay = document.getElementById('display');");
            htmlResponse.append("    popupDisplay.classList.toggle('open-popup1');");
            htmlResponse.append("}");
            htmlResponse.append("</script>");

            htmlResponse.append("<div id='movie-container' class='movie-container'>");
          
            for (int i = 0; i < results.size(); i++) {
                JsonObject movie = results.get(i).getAsJsonObject();
                
                String id = movie.get("id").getAsString();
                String title = escapeHtml(getJsonString(movie, "name"));
                String overview = escapeHtml(getJsonString(movie, "overview"));
                String releaseDate = escapeHtml(getJsonString(movie, "release_date"));
                String posterPath = getJsonString(movie, "poster_path");

                htmlResponse.append("<div class='card'>");
                String contextPath = request.getContextPath();
                htmlResponse.append("<a href='").append(contextPath).append("/TvShowDetails?id=").append(id).append("-").append(title).append("'>");

                if (!posterPath.isEmpty()) {
                    htmlResponse.append("<div class='img-container'>");
                    htmlResponse.append("<img src='https://image.tmdb.org/t/p/w500").append(posterPath).append("' alt='").append(title).append("'>");
                    htmlResponse.append("</div>");
                }

                htmlResponse.append("</a>");
                htmlResponse.append("<div class='scroll-container'>");
                htmlResponse.append("<div class='card-body'>");
                htmlResponse.append("<div class='card-title'>").append(title).append("</div>");
                htmlResponse.append("</div>");
                htmlResponse.append("</div>");
                htmlResponse.append("</div>");
            }

            htmlResponse.append("</div>");
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
