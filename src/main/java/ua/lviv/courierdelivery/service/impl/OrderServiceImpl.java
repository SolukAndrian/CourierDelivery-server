package ua.lviv.courierdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.courierdelivery.model.entity.Order;
import ua.lviv.courierdelivery.repository.OrderRepository;
import ua.lviv.courierdelivery.repository.UserRepository;
import ua.lviv.courierdelivery.service.OrderService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Apple on 10.01.2018.
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Order addOrder(Order order, Integer userId) {
        order.setUser(userRepository.findOne(userId));
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order updateOrder(Integer id, Order order) {
        Order order1 = findOne(id);
        order1.setDate(order.getDate());
        order1.setDescription(order.getDescription());
        order1.setPrice(order.getPrice());
        order1.setStatus(order.getStatus());
        order1.setUser(order.getUser());
        orderRepository.save(order1);
        return order1;
    }

    @Override
    public Order findOne(Integer id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        orderRepository.delete(orderRepository.findOne(id));
    }
}
