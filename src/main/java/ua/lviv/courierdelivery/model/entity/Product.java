package ua.lviv.courierdelivery.model.entity;

import ua.lviv.courierdelivery.model.base.BaseEntity;
import ua.lviv.courierdelivery.model.dto.impl.ProductDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    @Column(name = "weight_product")
    private Integer weightProduct;
//    @Lob()
//    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image")
    private Image image;

    @ManyToOne
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<FoodValue> foodValueList;

    public Product() {
    }

    public Product(String name, BigDecimal price, String description, Integer weightProduct) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.weightProduct = weightProduct;
    }

    public Product(String name, BigDecimal price, String description, Integer weightProduct, Image image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.weightProduct = weightProduct;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getWeightProduct() {
        return weightProduct;
    }

    public void setWeightProduct(Integer weightProduct) {
        this.weightProduct = weightProduct;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<FoodValue> getFoodValueList() {
        return foodValueList;
    }

    public void setFoodValueList(List<FoodValue> foodValueList) {
        this.foodValueList = foodValueList;
    }

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(productDTO.getName(),productDTO.getPrice(),productDTO.getDescription(), productDTO.getWeightProduct());
    }
}
