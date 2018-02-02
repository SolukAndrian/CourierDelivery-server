package ua.lviv.courierdelivery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.courierdelivery.model.dto.DTOBuilder;
import ua.lviv.courierdelivery.model.dto.impl.UserDTO;
import ua.lviv.courierdelivery.model.dto.impl.UserLinkDTO;
import ua.lviv.courierdelivery.model.entity.User;
import ua.lviv.courierdelivery.service.UserService;

import java.security.Principal;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Apple on 08.01.2018.
 */
@RestController
public class RegistrationController {
    @Autowired
    private UserService userService;

    @PostMapping("/api/registration")
    public ResponseEntity<UserLinkDTO> addUser(@RequestBody UserDTO userDTO, Principal principal) {
        User user = userService.addUser(new User(userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword(), userDTO.isEnabled(), userDTO.getFirstname(), userDTO.getLastname(), userDTO.getAddress()));
        Link selfLink = linkTo(methodOn(RegistrationController.class).getFoodValueById(user.getId())).withSelfRel();
        UserLinkDTO userLinkDTO = DTOBuilder.buildDtoForEntity(user, UserLinkDTO.class, selfLink);
        return new ResponseEntity<>(userLinkDTO, HttpStatus.CREATED);
    }


    @GetMapping(value = "/api/admin/users/findAll")
    public ResponseEntity<List<UserLinkDTO>> getAllUser() {
        List<User> allUsers = userService.findAllUsers();
        Link collectionLink = linkTo(methodOn(RegistrationController.class).getAllUser()).withSelfRel();
        List<UserLinkDTO> userLinkDTOS = DTOBuilder
                .buildDtoListForCollection(allUsers, UserLinkDTO.class, collectionLink);
        return new ResponseEntity<>(userLinkDTOS, HttpStatus.OK);
    }

    @GetMapping("/api/admin/users/{id}")
    public ResponseEntity<UserLinkDTO> getFoodValueById(@PathVariable Integer id) {
        User user = userService.findByIdUser(id);
        Link selfLink = linkTo(methodOn(RegistrationController.class).getFoodValueById(user.getId())).withRel("user");
        UserLinkDTO userLinkDTO = DTOBuilder.buildDtoForEntity(user, UserLinkDTO.class, selfLink);
        return new ResponseEntity<>(userLinkDTO, HttpStatus.OK);
    }
}
