package ua.lviv.courierdelivery.model.dto.impl;

import java.util.Date;

/**
 * Created by Apple on 10.01.2018.
 */
public class OrderDTO {
    private Date date;
    private String description;
    private Double price;
    private String status;
    private Integer userId;

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Integer getUserId() {
        return userId;
    }
}
