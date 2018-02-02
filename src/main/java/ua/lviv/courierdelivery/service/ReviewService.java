package ua.lviv.courierdelivery.service;

import ua.lviv.courierdelivery.model.entity.Review;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
public interface ReviewService {
    Review addReview(Review review, Integer orderId, Integer userId);

    void updateCategory(Integer id, Review review);

    Review getOrderReviewById(Integer id);

    List<Review> findAllReview();
}
