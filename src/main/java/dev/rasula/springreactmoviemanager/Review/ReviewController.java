package dev.rasula.springreactmoviemanager.Review;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Map<String, String> payload) {
        reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId"));
        return new ResponseEntity<>( "Review added", HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteReview(@RequestBody Map<String, ObjectId> payload) {
        reviewService.deleteReview(payload.get("reviewId"));
        return new ResponseEntity<>("Review deleted", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews(@RequestParam Map<String, String> params){
        return new ResponseEntity<>(reviewService.getReviews(params.get("imdbId")), HttpStatus.OK);
    }
}
