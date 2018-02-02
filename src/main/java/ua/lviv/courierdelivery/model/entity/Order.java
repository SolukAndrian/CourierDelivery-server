package ua.lviv.courierdelivery.model.entity;

import org.springframework.data.domain.jaxb.SpringDataJaxb;
import ua.lviv.courierdelivery.model.base.BaseEntity;
import ua.lviv.courierdelivery.model.dto.impl.OrderDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "delivery_price")
    private Double price;

    @NotNull
    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

    @OneToMany(mappedBy = "orderId", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Order() {
    }

    public Order(Date date, String description, Double price, String status) {
        this.date = date;
        this.description = description;
        this.price = price;
        this.status = status;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }


    public static Order toEntity(OrderDTO orderDto) {
        Date date = orderDto.getDate();
        return new Order(date, orderDto.getDescription(), orderDto.getPrice(), orderDto.getStatus());
    }

}
