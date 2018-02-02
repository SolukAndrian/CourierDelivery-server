package ua.lviv.courierdelivery.service;

import ua.lviv.courierdelivery.model.entity.FoodValue;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
public interface FoodValueService {
    FoodValue addFoodValue(FoodValue foodValue, Integer productId);

    FoodValue updateFoodValue(Integer id, FoodValue foodValue);

    FoodValue findOne(Integer id);

    List<FoodValue> findAll();

    void delete(Integer id);
}
