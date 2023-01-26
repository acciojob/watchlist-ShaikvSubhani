package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {

    Map<String,Movie> mov;
    Map<String,Director> dir;
    Map<String,List<String>> movdirmap;

    public MovieRepository()
    {
        this.mov=new HashMap<String,Movie>();
        this.dir=new HashMap<String,Director>();
        this.movdirmap=new HashMap<String,List<String>>();
    }

    //1> add movie
    public void addMovie(Movie movie)
    {
        String name=movie.getName();
        mov.put(name,movie);


    }
    //2> add director
    public void addDirector(Director director)
    {
        String name=director.getName();
        dir.put(name,director);

    }
    //3> add movie director pair
    public void addMovieDirectorPair(String movie,String director)
    {
        if(mov.containsKey(movie) && dir.containsKey(director))
        {
            List<String> currentMoviesByDirector=new ArrayList<>();
            if(movdirmap.containsKey(director))
                currentMoviesByDirector=movdirmap.get(director);

            currentMoviesByDirector=movdirmap.get(director);
            movdirmap.put(director,currentMoviesByDirector);
        }
    }
    //4>get movie
    public Movie getMovieByName(String name)
    {
        return mov.get(name);
    }

    //5>get director
    public Director getDirectorByName(String name)
    {
        return dir.get(name);
    }

    //6>get movies by director name;
    public List<String> getMoviesByDirectorName(String name)
    {
        List<String> movlist=new ArrayList<>();
        if(movdirmap.containsKey(name))
            movlist=movdirmap.get(name);

        return movlist;
    }

    //7>get list of all movies
    public List<String> findAllMovies()
    {
        return new ArrayList<>(mov.keySet());
    }

    //8>Delete a director and its movies from the record
    public void deleteDirectorByName(String name)
    {
        List<String> movies=new ArrayList<String>();
        //1-find the movie names by director from the pair
        if(movdirmap.containsKey(name))
        {
            movies=movdirmap.get(name);

            //2-deleting all the movies from movie db using movie name
            for(String movie : movies)
            {
                if(mov.containsKey(movie))
                {
                    mov.remove(movie);
                }
            }

            //3-deleting the pair
            movdirmap.remove(name);
        }
        //delete the director from director db.
        if(dir.containsKey(name))
        {
            dir.remove(name);
        }
    }

    //9>Delete all directors and all movies by them from the records
    public void deleteAllDirectors()
    {
        HashSet<String> movieset=new HashSet<>();

        //deleting the director's map
        dir=new HashMap<>();

        //finding out all the movies by all the directors combined
        for(String director: movdirmap.keySet())
        {
            //Iterating in the lists of movies by a director.
            for(String movie:movdirmap.get(director))
            {
                movieset.add(movie);
            }
        }

        //deleting the movie from the moviedb
        for(String movie: movieset)
        {
            if(mov.containsKey(movie))
            {
                mov.remove(movie);
            }
        }

        //clearing the pair
        movdirmap=new HashMap<>();
    }
}
