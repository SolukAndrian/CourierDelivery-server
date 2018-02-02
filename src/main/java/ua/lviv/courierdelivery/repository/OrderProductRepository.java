package ua.lviv.courierdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.courierdelivery.model.entity.OrderProduct;

/**
 * Created by Apple on 11.01.2018.
 */
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
}
