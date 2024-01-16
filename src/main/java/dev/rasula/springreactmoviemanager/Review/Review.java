package dev.rasula.springreactmoviemanager.Review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private ObjectId id;
    private ObjectId movieId;
    private String body;

    public Review(String body, ObjectId movieId) {
        this.body = body;
        this.movieId = movieId;
    }
}
