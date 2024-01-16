package dev.rasula.springreactmoviemanager.Rating;

import dev.rasula.springreactmoviemanager.Movie.Movie;
import dev.rasula.springreactmoviemanager.Movie.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private MovieService movieService;

    public Float getRatingsOfMovie(String imdbId){
        Optional<Movie> movie = movieService.singleMovie(imdbId);
        AtomicReference<Float> averageRating = new AtomicReference<>();

        movie.ifPresent(s -> {
            List<Rating> ratings = ratingRepository.findAllByMovieId(s.getId());
            float totalRating = 0;
            float ratingCount = 0;
            for (Rating rating : ratings) {
                ratingCount += 1;
                totalRating += rating.getRating();
            }
            if (ratingCount == 0) {
                averageRating.set((float) 0);
            } else {
                averageRating.set(totalRating / ratingCount);
            }
        });

        float f = averageRating.get();
        DecimalFormat df = new DecimalFormat("#.0");
        return Float.parseFloat(df.format(f));
    }

    public void addRating(String imdbId, String ratingValue){
        Optional<Movie> movie = movieService.singleMovie(imdbId);
        movie.ifPresent(s -> {
            ratingRepository.insert(new Rating(s.getId(), Integer.parseInt(ratingValue)));
        });
    }

    public void deleteRating(ObjectId ratingId){
        ratingRepository.deleteById(ratingId);
    }
}
