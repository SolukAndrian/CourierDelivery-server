package ua.lviv.courierdelivery.model.entity;

import ua.lviv.courierdelivery.model.base.BaseEntity;
import ua.lviv.courierdelivery.model.dto.impl.ImageDTO;

import javax.persistence.*;

/**
 * Created by Apple on 08.01.2018.
 */
@Entity
@Table(name = "image")
public class Image extends BaseEntity {

    @Column(name = "imagebase64", columnDefinition = "LONGTEXT")
    private String imagebase64;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User createdBy;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "is_used")
    private boolean isImageUsed;

    public Image() {
    }

    public Image(Integer id) {
        super(id);
    }

    public Image(Integer id, boolean isImageUsed) {
        super(id);
        this.isImageUsed = isImageUsed;
    }

    public Image(String imagebase64) {
        this.imagebase64 = imagebase64;
    }


    public Image(String imagebase64, String type) {
        this.imagebase64 = imagebase64;
        this.type = type;
    }

    public Image(Integer id, User createdBy) {
        super(id);
        this.createdBy = createdBy;
    }

    public Image(String imagebase64, String type, User createdBy, Long size) {
        this.imagebase64 = imagebase64;
        this.type = type;
        this.createdBy = createdBy;
        this.size = size;
    }

    public boolean isImageUsed() {
        return isImageUsed;
    }

    public void setImageUsed(boolean imageUsed) {
        isImageUsed = imageUsed;
    }

    public String getImagebase64() {
        return imagebase64;
    }

    public void setImagebase64(String imagebase64) {
        this.imagebase64 = imagebase64;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public boolean getIsImageUsed() {
        return isImageUsed;
    }

    public void setIsImageUsed(boolean imageUsed) {
        isImageUsed = imageUsed;
    }

    public static Image toEntity(ImageDTO imageDTO) {
        return new Image(imageDTO.getImageId());
    }
}
