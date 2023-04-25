import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.movies.Entity.Movies;

public interface MoviesRepository extends JpaRepository<Movies, Long> {

    List<Movie> findByMovieNameContaining(String movieName);

}
