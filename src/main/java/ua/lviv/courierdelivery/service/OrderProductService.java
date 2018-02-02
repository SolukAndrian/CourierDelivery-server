package ua.lviv.courierdelivery.service;

import ua.lviv.courierdelivery.model.entity.OrderProduct;

import java.util.List;

/**
 * Created by Apple on 11.01.2018.
 */
public interface OrderProductService {
    OrderProduct addOrderProduct(Integer orderId, Integer userId);

    OrderProduct findOne(Integer id);

    List<OrderProduct> findAll();
}
