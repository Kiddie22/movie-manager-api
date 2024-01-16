package dev.rasula.springreactmoviemanager.Rating;

import dev.rasula.springreactmoviemanager.Movie.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private ObjectId id;
    private ObjectId movieId;
    private int rating;

    public Rating(ObjectId movieId, Integer rating){
        this.movieId = movieId;
        this.rating = rating;
    }
}
