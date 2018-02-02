package ua.lviv.courierdelivery.model.entity;

import ua.lviv.courierdelivery.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OrderProduct extends BaseEntity {

    @ManyToOne
    private Product product;
    @ManyToOne
    private Order order;

    public OrderProduct() {
    }

    public OrderProduct(Product product, Order order) {
        this.product = product;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
