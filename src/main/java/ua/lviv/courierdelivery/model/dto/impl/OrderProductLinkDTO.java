package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.OrderProduct;

/**
 * Created by Apple on 11.01.2018.
 */
public class OrderProductLinkDTO extends DTO<OrderProduct> {
    public OrderProductLinkDTO(OrderProduct entity, Link link) {
        super(entity, link);
    }
    public Integer getOrderId() {
        return getEntity().getOrder().getId();
    }

    public Integer getProductId() {
        return getEntity().getProduct().getId();
    }
}
