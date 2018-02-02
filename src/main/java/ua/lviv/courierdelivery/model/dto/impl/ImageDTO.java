package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.Image;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 08.01.2018.
 */
public class ImageDTO extends DTO<Image> {
    public ImageDTO(Image entity, Link link) {
        super(entity, link);
        removeLinks();
//        getLinks().add(linkTo(methodOn(ImageController.class).getImageById(getEntity().getId())).withSelfRel());
    }

    public ImageDTO(Image entity) {
        super(entity);
    }

    public Integer getImageId() {
        return getEntity().getId();
    }

    public boolean getIsImageUsed() { return getEntity().getIsImageUsed(); }

}
