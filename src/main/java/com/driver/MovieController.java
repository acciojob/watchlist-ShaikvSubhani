package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;
    // to add movie
    @PostMapping("/movies/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie)
    {
        movieService.addMovie(movie);
        return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);

    }

    //to add director
    @PostMapping("/movies/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director)
    {
        movieService.addDirector(director);
        return new ResponseEntity<>("New director addes successfully", HttpStatus.CREATED);

    }

    @PutMapping("/movies/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie,@RequestParam("director") String director)
    {
        movieService.addMovieDirectorPair(movie,director);
        return new ResponseEntity<>("New movie-director pair added successfully",HttpStatus.CREATED);
    }

    //get movie
    @GetMapping("/movies/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String name)
    {
        Movie result=movieService.getMovieByName(name);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    //get director
    @GetMapping("/movies/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String name)
    {
        Director result=movieService.getDirectorByName(name);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName(@RequestParam("director") String name)
    {
        List<String> ans=movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(ans,HttpStatus.CREATED);
    }

    //get all movies
    @GetMapping("/movies/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies()
    {
        List<String> result=movieService.findAllMovies();
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam("director") String name)
    {
        movieService.deleteDirectorByName(name);
        return new ResponseEntity<>(name + " removed successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors()
    {
        movieService.deleteAllDirectors();
        return new ResponseEntity<>("All directors deleted successfully",HttpStatus.CREATED);
    }



}
