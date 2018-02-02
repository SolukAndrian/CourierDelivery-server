package ua.lviv.courierdelivery.model.entity;

import ua.lviv.courierdelivery.model.base.BaseEntity;
import ua.lviv.courierdelivery.model.dto.impl.FoodValueDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class FoodValue extends BaseEntity {
    @Column
    private Double proteins;
    @Column
    private Double fats;
    @Column
    private Double carbohydrates;
    @Column
    private Double cal;

    @ManyToOne
    private Product product;

    public FoodValue() {
    }

    public FoodValue(Double proteins, Double fats, Double carbohydrates, Double cal) {
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
        this.cal = cal;
    }

    public Double getProteins() {
        return proteins;
    }

    public void setProteins(Double proteins) {
        this.proteins = proteins;
    }

    public Double getFats() {
        return fats;
    }

    public void setFats(Double fats) {
        this.fats = fats;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getCal() {
        return cal;
    }

    public void setCal(Double cal) {
        this.cal = cal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public static FoodValue toEntity(FoodValueDTO foodValueDTO) {
        return new FoodValue(foodValueDTO.getProteins(),foodValueDTO.getFats(), foodValueDTO.getCarbohydrates(), foodValueDTO.getCal());
    }
}
