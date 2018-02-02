package ua.lviv.courierdelivery.service;

import ua.lviv.courierdelivery.model.entity.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(String nameCategory);

    Category updateCategory(Integer id, Category category);

    Category findOne(Integer id);

    List<Category> findAll();

    Category findByName(String name);

    void delete(Integer id);
}
