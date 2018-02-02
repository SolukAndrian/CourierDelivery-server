package ua.lviv.courierdelivery.service;

import ua.lviv.courierdelivery.model.entity.Product;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
public interface ProductService {
    Product addProduct(Product product, Integer categoryId);

    Product updateProduct(Integer id, Product product);

    Product findOne(Integer id);

    List<Product> findAll();

    void delete(Integer id);

    List<Product> findAllByCategoryId(Integer categoryId);
}
