package ua.lviv.courierdelivery.model.dto.impl;

/**
 * Created by Apple on 09.01.2018.
 */
public class ReviewDTO {
    private String review;
    private int rating;
    private Integer userId;
    private Integer orderId;

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getOrderId() {
        return orderId;
    }
}
