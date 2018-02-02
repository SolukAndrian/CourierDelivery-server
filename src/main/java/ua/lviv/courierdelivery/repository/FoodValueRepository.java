package ua.lviv.courierdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.courierdelivery.model.entity.FoodValue;

/**
 * Created by Apple on 08.01.2018.
 */
@Repository
public interface FoodValueRepository extends JpaRepository<FoodValue, Integer> {
}
