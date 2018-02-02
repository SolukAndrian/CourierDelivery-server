package ua.lviv.courierdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.courierdelivery.model.entity.Category;
import ua.lviv.courierdelivery.model.entity.Product;
import ua.lviv.courierdelivery.repository.ProductRepository;
import ua.lviv.courierdelivery.service.CategoryService;
import ua.lviv.courierdelivery.service.FoodValueService;
import ua.lviv.courierdelivery.service.ImageService;
import ua.lviv.courierdelivery.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FoodValueService foodValueService;
    @Autowired
    private ImageService imageService;


    @Override
    public Product addProduct(Product product, Integer categoryId) {
//        Integer imageId = product.getImage().getId();
//        imageService.setImageStatusInUse(imageId);
        product.setCategory(categoryService.findOne(categoryId));
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        Product product1 = new Product();
        product1.setCategory(product.getCategory());
        return productRepository.save(product1);
    }

    @Override
    public Product findOne(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public List<Product> findAllByCategoryId(Integer id) {
        return productRepository.findAllByCategoryId(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        productRepository.delete(productRepository.findOne(id));
    }
}
