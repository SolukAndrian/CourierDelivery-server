package ua.lviv.courierdelivery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.lviv.courierdelivery.model.entity.User;
import ua.lviv.courierdelivery.repository.UserRepository;
import ua.lviv.courierdelivery.service.UserService;
import ua.lviv.courierdelivery.utils.exception.ImageRepositorySizeQuotaExceededException;
import ua.lviv.courierdelivery.utils.exception.NotAuthorisedUserException;

import java.util.List;

/**
 * Created by Apple on 08.01.2018.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public void changeDataUser(Integer id, User user) throws NotAuthorisedUserException {

    }

    @Override
    public User findUserByUserName(String userName){
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public User findByIdUser(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User uploadImage(MultipartFile file) throws ImageRepositorySizeQuotaExceededException, NotAuthorisedUserException {
        return null;
    }

    @Override
    public User getAuthorizedUser() throws NotAuthorisedUserException {
        return null;
    }
}
