package ua.lviv.courierdelivery.service;

import org.springframework.web.multipart.MultipartFile;
import ua.lviv.courierdelivery.model.entity.User;
import ua.lviv.courierdelivery.utils.exception.ImageRepositorySizeQuotaExceededException;
import ua.lviv.courierdelivery.utils.exception.NotAuthorisedUserException;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
public interface UserService {
    User addUser(User user);

    void changeDataUser(Integer id, User user) throws NotAuthorisedUserException;

    User findUserByUserName(String userName);

    User findByIdUser(Integer id);

    List<User> findAllUsers();

    User uploadImage(MultipartFile file) throws ImageRepositorySizeQuotaExceededException,
            NotAuthorisedUserException;

    User getAuthorizedUser() throws NotAuthorisedUserException;
}
