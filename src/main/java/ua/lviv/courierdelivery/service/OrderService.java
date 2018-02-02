package ua.lviv.courierdelivery.service;

import ua.lviv.courierdelivery.model.entity.Order;

import java.util.List;

/**
 * Created by Apple on 10.01.2018.
 */
public interface OrderService {
    Order addOrder(Order order, Integer userId);

    Order updateOrder(Integer id, Order order);

    Order findOne(Integer id);

    List<Order> findAll();

    void delete(Integer id);
}
