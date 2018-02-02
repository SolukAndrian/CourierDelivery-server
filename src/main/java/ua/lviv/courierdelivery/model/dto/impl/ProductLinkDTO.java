package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.controller.ImageController;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.Image;
import ua.lviv.courierdelivery.model.entity.Product;

import java.math.BigDecimal;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 09.01.2018.
 */
public class ProductLinkDTO extends DTO<Product> {
    public ProductLinkDTO(Product entity, Link link) {
        super(entity, link);
    }
    public String getName() {
        return getEntity().getName();
    }

    public BigDecimal getPrice() {
        return getEntity().getPrice();
    }

    public String getDescription() {
        return getEntity().getDescription();
    }

    public Integer getWeightProduct() {
        return getEntity().getWeightProduct();
    }

//    public String getImage() {
//        return linkTo(methodOn(ImageController.class).getImageById(getEntity().getImage().getId())).withSelfRel().getHref();
//    }

    public Integer getCourseId() {
        return getEntity().getCategory().getId();
    }

}
