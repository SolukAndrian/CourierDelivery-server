package ua.lviv.courierdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.courierdelivery.model.entity.Review;
import ua.lviv.courierdelivery.repository.ReviewRepository;
import ua.lviv.courierdelivery.service.OrderService;
import ua.lviv.courierdelivery.service.ReviewService;
import ua.lviv.courierdelivery.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Override
    public Review addReview(Review review, Integer orderId, Integer userId) {
        review.setOrderId(orderService.findOne(orderId));
        review.setUser(userService.findByIdUser(userId));
        return reviewRepository.save(review);
    }

    @Override
    public void updateCategory(Integer id, Review review) {
        Review review1 = getOrderReviewById(id);
        review1.setRating(review.getRating());
        review1.setReview(review.getReview());
        review1.setOrderId(review.getOrderId());
        reviewRepository.save(review1);
    }

    @Override
    public Review getOrderReviewById(Integer id) {
        return reviewRepository.findOne(id);
    }

    @Override
    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }
}
