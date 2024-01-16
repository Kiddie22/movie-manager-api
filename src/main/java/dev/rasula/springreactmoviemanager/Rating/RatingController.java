package dev.rasula.springreactmoviemanager.Rating;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/movies/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @GetMapping
    public ResponseEntity<Float> getRatingsForMovie(@RequestBody Map<String, String> payload){
        return new ResponseEntity<>(ratingService.getRatingsOfMovie(payload.get("imdbId")), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addRating(@RequestBody Map<String, String> payload){
        ratingService.addRating(payload.get("imdbId"), payload.get("rating"));
        return new ResponseEntity<>("Added rating", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRating(@RequestBody Map<String, ObjectId> payload){
        ratingService.deleteRating(payload.get("ratingId"));
        return new ResponseEntity<>("Deleted rating", HttpStatus.OK);
    }
}
