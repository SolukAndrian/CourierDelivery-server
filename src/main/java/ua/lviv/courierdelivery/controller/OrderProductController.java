package ua.lviv.courierdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.courierdelivery.model.dto.DTOBuilder;
import ua.lviv.courierdelivery.model.dto.impl.OrderProductDTO;
import ua.lviv.courierdelivery.model.dto.impl.OrderProductLinkDTO;
import ua.lviv.courierdelivery.model.entity.OrderProduct;
import ua.lviv.courierdelivery.service.OrderProductService;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 11.01.2018.
 */
@RestController
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;

    @PostMapping("/api/admin/add/orderProduct")
    public ResponseEntity<OrderProductLinkDTO> addOrderProduct(@RequestBody OrderProductDTO orderProductDTO) {
        OrderProduct orderProduct = orderProductService
                .addOrderProduct(orderProductDTO.getOrderId(), orderProductDTO.getProductId());
        Link selfLink = linkTo(methodOn(OrderProductController.class).getOrderProductById(orderProduct.getId())).withSelfRel();
        OrderProductLinkDTO orderProductLinkDTO = DTOBuilder.buildDtoForEntity(orderProduct, OrderProductLinkDTO.class, selfLink);
        return new ResponseEntity<>(orderProductLinkDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/api/admin/orderProduct/findAll")
    public ResponseEntity<List<OrderProductLinkDTO>> getAllOrderProduct() {
        List<OrderProduct> productList = orderProductService.findAll();
        Link collectionLink = linkTo(methodOn(OrderProductController.class).getAllOrderProduct()).withSelfRel();
        List<OrderProductLinkDTO> linkDTOList = DTOBuilder
                .buildDtoListForCollection(productList, OrderProductLinkDTO.class, collectionLink);
        return new ResponseEntity<>(linkDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/api/admin/orderProduct/{id}")
    public ResponseEntity<OrderProductLinkDTO> getOrderProductById(@PathVariable Integer id) {
        OrderProduct orderProduct = orderProductService.findOne(id);
        Link selfLink = linkTo(methodOn(OrderProductController.class).getOrderProductById(orderProduct.getId())).withRel("orderProduct");
        OrderProductLinkDTO orderProductLinkDTO = DTOBuilder.buildDtoForEntity(orderProduct, OrderProductLinkDTO.class, selfLink);
        return new ResponseEntity<>(orderProductLinkDTO, HttpStatus.OK);
    }
}
