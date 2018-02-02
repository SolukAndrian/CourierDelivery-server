package ua.lviv.courierdelivery.model.entity;

import ua.lviv.courierdelivery.model.base.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "reviews")
public class Review extends BaseEntity {

    @NotNull
    @Column(name = "review")
    private String review;

    @Column(name = "rating")
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order orderId;

    public Review() {
    }

    public Review(String review, Integer rating) {
        this.review = review;
        this.rating = rating;
    }

    public Review(String review, Integer rating, User user, Order orderId) {
        this.review = review;
        this.rating = rating;
        this.user = user;
        this.orderId = orderId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setOrderId(Order orderId) {
        this.orderId = orderId;
    }

    //    @Override
//    public String toString() {
//        return "Review{" +
//                "review='" + review + '\'' +
//                ", rating=" + rating +
//                ", user=" + user +
//                '}';
//    }
}
