package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.Review;

/**
 * Created by Apple on 09.01.2018.
 */
public class ReviewLinkDTO extends DTO<Review> {
    public ReviewLinkDTO(Review entity, Link link) {
        super(entity, link);
    }

    public String getReview() {
        return getEntity().getReview();
    }

    public int getRating() {
        return getEntity().getRating();
    }

    public Integer getAccountEmail() {
        return getEntity().getUser().getId();
    }

    public Integer getOrder() {
        return getEntity().getOrderId().getId();
    }
}
