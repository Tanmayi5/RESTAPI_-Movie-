
// import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Movies.Repository.MoviesRepository;

// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import com.example.movies.model.Movie; // Replace with your Movie model class
// import com.example.movies.repository.MovieRepository; // Replace with your MovieRepository interface

// import com.learn.movies.Entity.Movies;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    @Autowired
    private MoviesRepository movieRepository;

    @GetMapping
    public List<Movies> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") String id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return ResponseEntity.ok(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieRepository.save(movie);
        return ResponseEntity.created(URI.create("/movies/" + createdMovie.getId())).body(createdMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable("id") String id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{title}")
    public List<Movie> getMoviesByTitle(@PathVariable("title") String title) {
        List<Movie> movies = movieRepository.findByTitleContainingIgnoreCase(title);
        if (movies.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return movies;
        }
    }
}
