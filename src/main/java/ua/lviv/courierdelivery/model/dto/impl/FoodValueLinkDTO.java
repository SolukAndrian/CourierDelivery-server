package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.FoodValue;

/**
 * Created by Apple on 08.01.2018.
 */
public class FoodValueLinkDTO extends DTO<FoodValue> {
    public FoodValueLinkDTO(FoodValue entity, Link link) {
        super(entity, link);
    }

    public Double getProteins() {
        return getEntity().getProteins();
    }

    public Double getFats() {
        return getEntity().getFats();
    }

    public Double getCarbohydrates() {
        return getEntity().getCarbohydrates();
    }

    public Double getCal() {
        return getEntity().getCal();
    }

    public Integer getProductId() {
        return getEntity().getProduct().getId();
    }
}
