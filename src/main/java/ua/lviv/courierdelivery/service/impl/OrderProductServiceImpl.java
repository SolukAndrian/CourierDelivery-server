package ua.lviv.courierdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.courierdelivery.model.entity.OrderProduct;
import ua.lviv.courierdelivery.repository.OrderProductRepository;
import ua.lviv.courierdelivery.service.OrderProductService;
import ua.lviv.courierdelivery.service.OrderService;
import ua.lviv.courierdelivery.service.ProductService;

import java.util.List;

/**
 * Created by Apple on 11.01.2018.
 */
@Service
public class OrderProductServiceImpl implements OrderProductService{
    @Autowired
    private OrderProductRepository orderProductRepository;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Override
    public OrderProduct addOrderProduct(Integer orderId, Integer userId) {
        OrderProduct orderProduct = new OrderProduct(productService.findOne(userId), orderService.findOne(orderId));
        return orderProductRepository.save(orderProduct);
    }

    @Override
    public OrderProduct findOne(Integer id) {
        return orderProductRepository.findOne(id);
    }

    @Override
    public List<OrderProduct> findAll() {
        return orderProductRepository.findAll();
    }

}
