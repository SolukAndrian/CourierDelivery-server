package ua.lviv.courierdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.courierdelivery.model.entity.Category;
import ua.lviv.courierdelivery.repository.CategoryRepository;
import ua.lviv.courierdelivery.service.CategoryService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Category addCategory(String nameCategory) {
        Category category = new Category(nameCategory);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Integer id, Category category) {
        Category category1 = findOne(id);
        category1.setName(category.getName());
        category1.setProducts(category.getProducts());
        return categoryRepository.save(category1);
    }

    @Override
    public Category findOne(Integer id) {
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        categoryRepository.delete(categoryRepository.findOne(id));
    }
}
