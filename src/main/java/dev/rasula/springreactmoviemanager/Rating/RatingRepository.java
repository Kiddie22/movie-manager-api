package dev.rasula.springreactmoviemanager.Rating;

import dev.rasula.springreactmoviemanager.Movie.Movie;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends MongoRepository<Rating, ObjectId> {
    List<Rating> findAllByMovieId(ObjectId movieId);
}
