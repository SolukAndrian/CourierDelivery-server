package ua.lviv.courierdelivery.model.entity;

import ua.lviv.courierdelivery.model.base.BaseEntity;
import ua.lviv.courierdelivery.model.dto.impl.CategoryDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Entity
public class Category extends BaseEntity {
    @Column(name = "category_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private Set<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getName());
    }
}
