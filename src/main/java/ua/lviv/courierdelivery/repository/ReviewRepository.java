package ua.lviv.courierdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.courierdelivery.model.entity.Review;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query(value = "select avg(c.rating) from Review c where c.orderId.id =:orderId group by c.order.id", nativeQuery = true)
    Double findRatingByOrder_Id(@Param("orderId") Integer orderId);
}
