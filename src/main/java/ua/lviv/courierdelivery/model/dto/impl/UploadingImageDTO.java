package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.Image;

/**
 * Created by Apple on 08.01.2018.
 */
public class UploadingImageDTO extends DTO<Image> {

    private Integer bytesLeft;

    public UploadingImageDTO(Image entity, Link link) {
        super(entity, link);
    }

    public Integer getImageId() {
        return getEntity().getId();
    }

    public Integer getOwnerId() {
        return getEntity().getCreatedBy().getId();
    }

    public Integer getBytesLeft() {
        return bytesLeft;
    }

    public void setBytesLeft(Integer bytesLeft) {
        this.bytesLeft = bytesLeft;
    }
}

