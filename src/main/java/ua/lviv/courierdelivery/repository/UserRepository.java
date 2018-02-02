package ua.lviv.courierdelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.lviv.courierdelivery.model.entity.User;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where u.username like :userName")
    User findUserByUserName(@Param("userName") String userName);
}
