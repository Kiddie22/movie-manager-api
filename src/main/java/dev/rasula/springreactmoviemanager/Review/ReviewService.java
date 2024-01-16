package dev.rasula.springreactmoviemanager.Review;

import dev.rasula.springreactmoviemanager.Movie.Movie;
import dev.rasula.springreactmoviemanager.Movie.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieService movieService;

    public void createReview(String reviewBody, String imdbId){
        Optional<Movie> movie = movieService.singleMovie(imdbId);

        movie.ifPresent(s -> {
            reviewRepository.insert(new Review(reviewBody, s.getId()));
        });
    }

    public void deleteReview(ObjectId reviewId){
        Optional<Review> review = reviewRepository.findById(reviewId);
        review.ifPresent(s -> {
            reviewRepository.deleteById(reviewId);
        });
    }

    public List<Review> getReviews(String imdbId){
        Optional<Movie> movie = movieService.singleMovie(imdbId);
        List<Review> reviews = new ArrayList<>();
        movie.ifPresent(s -> {
            List<Review> fetchedReviews = reviewRepository.findAllByMovieId(s.getId());
            reviews.addAll(fetchedReviews);
        });
        return reviews;
    }
}
