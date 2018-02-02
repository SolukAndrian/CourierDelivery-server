package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.controller.CategoryController;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.Category;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 06.01.2018.
 */
public class CategoryLinkDTO extends DTO<Category> {

    public CategoryLinkDTO(Category entity, Link link) {
        super(entity, link);
        add(linkTo(methodOn(CategoryController.class).getAllCategories()).withRel("courses"));
    }

    public String getName() {
        return getEntity().getName();
    }

}
