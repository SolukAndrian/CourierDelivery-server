package ua.lviv.courierdelivery.model.dto.impl;

import ua.lviv.courierdelivery.model.entity.Image;

import java.math.BigDecimal;

/**
 * Created by Apple on 08.01.2018.
 */
public class ProductDTO {
    private String name;
    private BigDecimal price;
    private String description;
    private Integer weightProduct;
//    private Image image;
    private Integer categoryId;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getWeightProduct() {
        return weightProduct;
    }

//    public Image getImage() {
//        return image;
//    }

    public Integer getCategoryId() {
        return categoryId;
    }
}
