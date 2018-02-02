package ua.lviv.courierdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.courierdelivery.model.entity.Product;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    @Query("select p from Product p where p.category.id like :categoryId")
    List<Product> findAllByCategoryId(@Param("categoryId") Integer categoryId);
}
