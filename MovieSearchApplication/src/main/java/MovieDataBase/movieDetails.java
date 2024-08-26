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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


public class movieDetails extends HttpServlet 
{
	private static final String TMDB_API_KEY = "e4fab66da9f615c07892ead6b42a792f"; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("servlet is called");
        String movieId = request.getParameter("id");
        System.out.println(movieId);
        if (movieId == null || movieId.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Movie ID is missing");
            return;
        }
   
        String apiUrl = "https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + TMDB_API_KEY;
        

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
         System.out.println(apiUrl);
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
            JsonObject movie = gson.fromJson(content.toString(), JsonObject.class);

            // Start building the HTML response
            StringBuilder htmlResponse = new StringBuilder();
            htmlResponse.append("<!DOCTYPE html>");
            htmlResponse.append("<html lang='en'>");
            htmlResponse.append("<head>");
            htmlResponse.append("<meta charset='UTF-8'>");
            htmlResponse.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            htmlResponse.append("<title>").append(escapeHtml(getJsonString(movie, "title"))).append("</title>");
            htmlResponse.append("<link rel='stylesheet' type='text/css' href='MovieDetails.css'>");
            htmlResponse.append("<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css\" integrity=\"sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==\" crossorigin=\"anonymous\" referrerpolicy=\"no-referrer\" />");
            htmlResponse.append("<style>");
            htmlResponse.append("body {");
            htmlResponse.append("margin: 0;");
            htmlResponse.append("font-family: Arial, sans-serif;");
            htmlResponse.append("}");

            htmlResponse.append("#search {");
            htmlResponse.append("width: 70%;");
            htmlResponse.append("height: 40px;");
            htmlResponse.append("border-radius: 18px;");
            htmlResponse.append("border:none;");
            htmlResponse.append("padding: 0 10px;"); // Add padding for better appearance
            htmlResponse.append("}");

            htmlResponse.append("#bsearch {");
            htmlResponse.append("width: 10%;");
            htmlResponse.append("height: 40px;");
            htmlResponse.append("border-radius: 18px;");
            htmlResponse.append("cursor: pointer;");
            htmlResponse.append("border:none;");
            htmlResponse.append("position:relative;");
            htmlResponse.append("right:10.9%;");
            htmlResponse.append("}");

            htmlResponse.append("header {");
            htmlResponse.append("display: flex;");
            htmlResponse.append("justify-content: space-between;"); // Use space-between to distribute space between elements
            htmlResponse.append("align-items: center;"); // Align items vertically in the center
            htmlResponse.append("}");

            htmlResponse.append("a {");
            htmlResponse.append("text-decoration: none;");
            htmlResponse.append("color: white;");
            htmlResponse.append("}");

            htmlResponse.append(".main {");
            htmlResponse.append("background-color: rgb(60, 61, 55);");
            htmlResponse.append("padding-bottom: 2%;");
            htmlResponse.append("padding-top: 1%;");
            htmlResponse.append("}");
            
            htmlResponse.append(".mgenre {");
            htmlResponse.append("display: flex;");
            htmlResponse.append("justify-content: space-around;");
            htmlResponse.append("width: 40%;");
            htmlResponse.append("border: 2px solid white;");
            htmlResponse.append("position: relative;");
            htmlResponse.append("left: 10%;");
            htmlResponse.append("border-radius: 8px;");
            htmlResponse.append("display: none;");
            htmlResponse.append("}");

            htmlResponse.append(".navbarcontainer {");
            htmlResponse.append("width: 50%;");
            htmlResponse.append("display: flex;");
            htmlResponse.append("justify-content: space-evenly;");
            htmlResponse.append("}");

            htmlResponse.append("form {");
            htmlResponse.append("width: 30%;");
            htmlResponse.append("}");

            htmlResponse.append(".open-popup {");
            htmlResponse.append("display: block;");
            htmlResponse.append("display: flex;");
            htmlResponse.append("justify-content: space-around;");
            htmlResponse.append("width: 40%;");
            htmlResponse.append("border: 2px solid white;");
            htmlResponse.append("position: relative;");
            htmlResponse.append("left: 10%;");
            htmlResponse.append("border-radius: 8px;");
            htmlResponse.append("}");

            htmlResponse.append(".Tvgenre {");
            htmlResponse.append("display: none;");
            htmlResponse.append("}");

            htmlResponse.append(".open-popup1 {");
            htmlResponse.append("display: block;");
            htmlResponse.append("display: flex;");
            htmlResponse.append("justify-content: space-around;");
            htmlResponse.append("width: 40%;");
            htmlResponse.append("border: 2px solid white;");
            htmlResponse.append("position: relative;");
            htmlResponse.append("left: 20%;");
            htmlResponse.append("border-radius: 8px;");
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
            htmlResponse.append("<h1><a href=\"MoviePage.jsp\"><img  src=\"https://r2.erweima.ai/ideogram/L3rDFPRHRHeyOVVEJA7p7w.jpg\" style=\"width:160px; height:55px;\"></a></h1>");
            htmlResponse.append("</div>");
            htmlResponse.append("<div>");
            htmlResponse.append("<h2><a href=\"#\" onClick=\"openPopup()\">Movies</a></h2>");
            htmlResponse.append("</div>");
            htmlResponse.append("<div>");
            htmlResponse.append("<h2><a href=\"#\" onClick=\"openPopup1()\">Web Series</a></h2>");
            htmlResponse.append("</div>");
            htmlResponse.append("</div>");
            htmlResponse.append("<form action=\"TmdbApiCall\" method=\"get\">");
            htmlResponse.append("<input type=\"search\" id=\"search\" style=\"margin-left: -40px;\" name=\"query\">");
            htmlResponse.append("<button type=\"submit\" id=\"bsearch\">");
            htmlResponse.append("<i class=\"fa-sharp-duotone fa-solid fa-magnifying-glass\"></i>");
            htmlResponse.append("</button>");
            htmlResponse.append("</form>");
            htmlResponse.append("</header>");

            htmlResponse.append("<div class=\"mgenre\" id=\"show\">");
            htmlResponse.append("<h2><a href=\"NowPlayingM\">Now Playing</a></h2>");
            htmlResponse.append("<h2><a href=\"TrendingMovies\">Trending Movies</a></h2>");
            htmlResponse.append("<h2><a href=\"UpComingM\">Upcoming</a></h2>");
            htmlResponse.append("</div>");

            htmlResponse.append("<div class=\"Tvgenre\" id=\"display\">");
            htmlResponse.append("<h2><a href=\"PopularTv\">Popular</a></h2>");
            htmlResponse.append("<h2><a href=\"TopRatedTv\">Top Rated</a></h2>");
            htmlResponse.append("<h2><a href=\"OnTheAirTv\">On The Air</a></h2>");
            htmlResponse.append("</div>");

            htmlResponse.append("</div>");
            
            String title = escapeHtml(getJsonString(movie, "title"));
            String overview = escapeHtml(getJsonString(movie, "overview"));
            String releaseDate = escapeHtml(getJsonString(movie, "release_date"));
            String runtime = escapeHtml(getJsonString(movie, "runtime"));
            String ratings= escapeHtml(getJsonString(movie, "vote_average"));
            System.out.println(ratings);
            System.out.println(movie.toString());
            htmlResponse.append("<div class='container'>");
            String posterPath = getJsonString(movie, "poster_path");

            htmlResponse.append("<h1>").append(title).append("</h1>");
            
            htmlResponse.append("<img src='https://image.tmdb.org/t/p/w500").append(posterPath).append("' alt='").append(title).append("'>");
            htmlResponse.append("<p><strong>Release Date: </strong> ").append(releaseDate).append("</p>");
            htmlResponse.append("<p><strong>Overview: </strong>").append(overview).append("</p>");
            htmlResponse.append("<p><strong>Runtime: </strong>").append(runtime).append(" minutes </p>");
            htmlResponse.append("<p><strong>Rating: </strong>").append(ratings).append("</p>");
            htmlResponse.append("</div>");
            
            htmlResponse.append("</body></html>");

            // Send the HTML response back to the client
            response.setContentType("text/html");
            response.getWriter().write(htmlResponse.toString());

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Failed to fetch movie details from TMDB API");
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
