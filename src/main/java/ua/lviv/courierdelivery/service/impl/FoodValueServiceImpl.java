package ua.lviv.courierdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.courierdelivery.model.entity.FoodValue;
import ua.lviv.courierdelivery.repository.FoodValueRepository;
import ua.lviv.courierdelivery.service.FoodValueService;
import ua.lviv.courierdelivery.service.ProductService;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Service
public class FoodValueServiceImpl implements FoodValueService {
    @Autowired
    private FoodValueRepository foodValueRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public FoodValue addFoodValue(FoodValue foodValue, Integer productId) {
        foodValue.setProduct(productService.findOne(productId));
        return foodValueRepository.save(foodValue);
    }

    @Override
    public FoodValue updateFoodValue(Integer id, FoodValue foodValue) {
        FoodValue foodValue1 = findOne(id);
        foodValue1.setCal(foodValue.getCal());
        foodValue1.setCarbohydrates(foodValue.getCarbohydrates());
        foodValue1.setFats(foodValue.getFats());
        foodValue1.setProteins(foodValue.getProteins());
        return foodValueRepository.save(foodValue1);
    }

    @Override
    public FoodValue findOne(Integer id) {
        return foodValueRepository.findOne(id);
    }

    @Override
    public List<FoodValue> findAll() {
        return foodValueRepository.findAll();
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        foodValueRepository.delete(foodValueRepository.findOne(id));
    }
}
