package ua.lviv.courierdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.courierdelivery.model.entity.Order;

/**
 * Created by Apple on 10.01.2018.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
