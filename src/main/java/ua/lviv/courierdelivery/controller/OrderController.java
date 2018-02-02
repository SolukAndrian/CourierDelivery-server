package ua.lviv.courierdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.courierdelivery.model.dto.DTOBuilder;
import ua.lviv.courierdelivery.model.dto.impl.OrderDTO;
import ua.lviv.courierdelivery.model.dto.impl.OrderLinkDTO;
import ua.lviv.courierdelivery.model.entity.Order;
import ua.lviv.courierdelivery.service.OrderService;

import java.security.Principal;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 10.01.2018.
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/api/private/add/order")
    public ResponseEntity<OrderLinkDTO> addFoodValue(@RequestBody OrderDTO orderDTO) {
        Order order = orderService
                .addOrder(new Order(orderDTO.getDate(),orderDTO.getDescription(),orderDTO.getPrice(),orderDTO.getStatus()), orderDTO.getUserId());
        Link selfLink = linkTo(methodOn(FoodValueController.class).getFoodValueById(order.getId())).withSelfRel();
        OrderLinkDTO orderLinkDTO = DTOBuilder.buildDtoForEntity(order, OrderLinkDTO.class, selfLink);
        return new ResponseEntity<>(orderLinkDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/api/admin/order/findAll")
    public ResponseEntity<List<OrderLinkDTO>> getAllFoodValues() {
        List<Order> foodValueList = orderService.findAll();
        Link collectionLink = linkTo(methodOn(FoodValueController.class).getAllFoodValues()).withSelfRel();
        List<OrderLinkDTO> foodValueLinkDTOS = DTOBuilder
                .buildDtoListForCollection(foodValueList, OrderLinkDTO.class, collectionLink);
        return new ResponseEntity<>(foodValueLinkDTOS, HttpStatus.OK);
    }

    @GetMapping("/api/admin/orders/{id}")
    public ResponseEntity<OrderLinkDTO> getFoodValueById(@PathVariable Integer id) {
        Order foodValue = orderService.findOne(id);
        Link selfLink = linkTo(methodOn(FoodValueController.class).getFoodValueById(foodValue.getId())).withRel("foodValue");
        OrderLinkDTO foodValueLinkDTO = DTOBuilder.buildDtoForEntity(foodValue, OrderLinkDTO.class, selfLink);
        return new ResponseEntity<>(foodValueLinkDTO, HttpStatus.OK);
    }

//    @PutMapping("/api/admin/update/foodValue/{id}")
//    public ResponseEntity<OrderLinkDTO> updateFoodValue(@RequestBody OrderDTO categoryDTO,
//                                                            @PathVariable Integer id) {
//        Order foodValue = orderService.updateOrder(id, Order.toEntity(categoryDTO));
//        Link selfLink = linkTo(methodOn(FoodValueController.class).getFoodValueById(foodValue.getId())).withSelfRel();
//        OrderLinkDTO foodValueLinkDTO = DTOBuilder.buildDtoForEntity(foodValue, OrderLinkDTO.class, selfLink);
//        return new ResponseEntity<>(foodValueLinkDTO, HttpStatus.OK);
//    }

    @DeleteMapping(value = "/api/admin/delete/orders/{orderId}")
    public void deleteFoodValue(@PathVariable Integer orderId) {
        orderService.delete(orderId);
    }
}
