package ua.lviv.courierdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.courierdelivery.model.dto.DTOBuilder;
import ua.lviv.courierdelivery.model.dto.impl.ReviewDTO;
import ua.lviv.courierdelivery.model.dto.impl.ReviewLinkDTO;
import ua.lviv.courierdelivery.model.entity.Review;
import ua.lviv.courierdelivery.service.OrderService;
import ua.lviv.courierdelivery.service.ReviewService;
import ua.lviv.courierdelivery.service.UserService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 08.01.2018.
 */
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/api/private/add/review")
    public ResponseEntity<ReviewLinkDTO> addReview(@RequestBody ReviewDTO reviewDTO) {
        Review review = reviewService.addReview(new Review(reviewDTO.getReview(), reviewDTO.getRating()),reviewDTO.getOrderId(), reviewDTO.getUserId());
        Link selfLink = linkTo(methodOn(ProductController.class).getProductById(review.getId())).withSelfRel();
        ReviewLinkDTO reviewLinkDTO = DTOBuilder.buildDtoForEntity(review, ReviewLinkDTO.class, selfLink);
        return new ResponseEntity<>(reviewLinkDTO, HttpStatus.CREATED);
    }

    @GetMapping("/api/admin/review/{reviewId}")
    public ResponseEntity<ReviewLinkDTO> getFoodValueById(@PathVariable Integer reviewId) {
        Review review = reviewService.getOrderReviewById(reviewId);
        Link selfLink = linkTo(methodOn(RegistrationController.class).getFoodValueById(review.getId())).withRel("review");
        ReviewLinkDTO reviewLinkDTO = DTOBuilder.buildDtoForEntity(review, ReviewLinkDTO.class, selfLink);
        return new ResponseEntity<>(reviewLinkDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/admin/get/reviews/findAll")
    public ResponseEntity<List<ReviewLinkDTO>> getAllReviews() {
        List<Review> allUsers = reviewService.findAllReview();
        Link collectionLink = linkTo(methodOn(ReviewController.class).getAllReviews()).withSelfRel();
        List<ReviewLinkDTO> reviewLinkDTOS = DTOBuilder
                .buildDtoListForCollection(allUsers, ReviewLinkDTO.class, collectionLink);
        return new ResponseEntity<>(reviewLinkDTOS, HttpStatus.OK);
    }

}
