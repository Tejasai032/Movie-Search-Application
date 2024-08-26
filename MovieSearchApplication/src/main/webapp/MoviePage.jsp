<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>MOVIE SITE</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

<style>

*
{
	font-family: Poppins, sans-serif;
}

  #search {
        width: 70%;
        height: 40px;
        border-radius: 18px;
        border: none;
    }

    #bsearch {
        width: 10%;
        height: 40px;
        border-radius: 18px;
        cursor: pointer;
        position: relative;
        right: 10.9%;
        border: none;
    }

    header {
        display: flex;
        justify-content: space-around;
    }

    #icon {
        position: relative;
    }

    a {
        text-decoration: none;
        color: white;
    }

    .main {
        background-color: rgb(60, 61, 55);
        padding-bottom: 2%;
        padding-top: 1%;
    }

    .mgenre, .Tvgenre {
        display: none;
        flex-direction: column;
        justify-content: space-around;
        border: 2px solid white;
        border-radius: 8px;
        font-size: 8px;
        position: absolute;
        background-color: rgb(60, 61, 55);
        padding: 10px;
    }

    .mgenre {
        width: 20%;
        left: 20%;
    }

    .Tvgenre {
        width: 15%;
        left: 42%;
    }

    .open-popup {
        display: flex;
    }

    .open-popup1 {
        display: flex;
    }

    .navbarcontainer {
        width: 50%;
        display: flex;
        justify-content: space-between;
    }

    form {
        width: 30%;
        
    }

    .view {
        display: inline;
    }
    
    .heading {
     text-align: center;
     color:white;
    }
    
    .carousel-control-prev-icon,
    .carousel-control-next-icon {
      filter: brightness(150%);
      opacity: 0.8;
    }

    .carousel-control-prev:hover .carousel-control-prev-icon,
    .carousel-control-next:hover .carousel-control-next-icon {
      opacity: 1;
    }

    .carousel-control-prev,
    .carousel-control-next {
      background-color: rgba(0, 0, 0, 0.3);
      width: 5%;
    }

    .carousel-control-prev:hover,
    .carousel-control-next:hover {
      background-color: rgba(0, 0, 0, 0.5);
    }
    
    .card {
      margin: 10px;
      width: 100%;
      cursor:pointer;
      height:280px;
      transition:transform 0.8s;
    }
    
    .display
    {
     background-color:rgb(30, 32, 30) ;
    }
    
    .card-img-top
    {
      height:278px;
      border-radius:8px;
    }
    
    .card:hover
    {
      transform:scale(1.05);
      
    }
    
    .heading
    {
      text-align:center;
      color:black;
    }
    .home
    {
      width:100%;
      height:50px;
    }
    
 
    
    
</style>
</head>
<body>
<div class="main">
  <header>
  <div class="navbarcontainer">
    <div id="icon"> 
        <h2><a href="MoviePage.jsp"><img src="https://r2.erweima.ai/ideogram/L3rDFPRHRHeyOVVEJA7p7w.jpg" class="home"></a></h2>
    </div>
    <h2><a href="#" onClick="openPopup()">Movies</a></h2>
    <h2><a href="#" onClick="openPopup1()">Web Series</a></h2>
    </div>
   <form action="TmdbApiCall" method="get">
        <input type="search" id="search" name="query"> 
        <button type="submit" id="bsearch">
            <i class="fa-sharp-duotone fa-solid fa-magnifying-glass"></i>
        </button> 
 </form>
 
  </header>
  <div class="view">
   <div class="mgenre"  id="show">
       <h2><a style="font-size: 18px;" href="NowPlayingM">Now Playing</a></h2>
       <h2><a style="font-size: 18px;" href="TreandingMovies">Trending Movies</a></h2>
       <h2><a style="font-size: 18px;" href="UpComingM">Upcoming</a></h2>
    </div>
     <div class="Tvgenre" id="display">
       <h2><a  style="font-size: 18px;" href="PopularTv">Popular</a></h2>
       <h2><a style="font-size: 18px;" href="TopRatedTv">Top Rated</a></h2>
       <h2><a  style="font-size: 18px;" href="OnTheAirTv">On The Air</a></h2>
    </div>
    </div>
</div> 

<div class="Popular">
 <h1 class="heading">Popular Movies</h1>
<iframe src="PopularM" style="width:100%; height:450px;"></iframe>
</div>

<div class="TopRated">
 <h1 class="heading">Top Rated Movies</h1>
<iframe src="TopRated" style="width:100%; height:450px;"></iframe>
</div>

<script>
    function openPopup() {
        let popup = document.getElementById("show");
        popup.classList.toggle("open-popup");
    }

    function openPopup1() {
        let popupDisplay = document.getElementById("display");
        popupDisplay.classList.toggle("open-popup1");
    }
</script>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const container = document.getElementById('movie-container');
        let isMouseDown = false;
        let startX, scrollLeft;

        container.addEventListener('mousedown', (e) => {
            isMouseDown = true;
            container.classList.add('active');
            startX = e.pageX - container.offsetLeft;
            scrollLeft = container.scrollLeft;
        });

        container.addEventListener('mouseleave', () => {
            isMouseDown = false;
            container.classList.remove('active');
        });

        container.addEventListener('mouseup', () => {
            isMouseDown = false;
            container.classList.remove('active');
        });

        container.addEventListener('mousemove', (e) => {
            if (!isMouseDown) return;
            e.preventDefault();
            const x = e.pageX - container.offsetLeft;
            const scroll = (x - startX) * 2; // Adjust multiplier for speed
            container.scrollLeft = scrollLeft - scroll;
        });
    });
</script>

</body>
</html>