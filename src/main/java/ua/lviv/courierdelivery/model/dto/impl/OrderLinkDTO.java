package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.Order;

import java.util.Date;

/**
 * Created by Apple on 10.01.2018.
 */
public class OrderLinkDTO extends DTO<Order> {
    public OrderLinkDTO(Order entity, Link link) {
        super(entity, link);
    }
    public Date getDate() {
        return getEntity().getDate();
    }

    public String getDescription() {
        return getEntity().getDescription();
    }

    public Double getPrice() {
        return getEntity().getPrice();
    }

    public String getStatus() {
        return getEntity().getStatus();
    }

    public Integer getUserId() {
        return getEntity().getUser().getId();
    }
}
