package ua.lviv.courierdelivery.model.dto.impl;

import org.springframework.hateoas.Link;
import ua.lviv.courierdelivery.model.dto.DTO;
import ua.lviv.courierdelivery.model.entity.User;

/**
 * Created by Apple on 10.01.2018.
 */
public class UserLinkDTO extends DTO<User> {
    public UserLinkDTO(User entity, Link link) {
        super(entity, link);
    }
    public String getUsername() {
        return getEntity().getUsername();
    }

    public String getPassword() {
        return getEntity().getPassword();
    }

    public String getFirstname() {
        return getEntity().getFirstname();
    }

    public String getLastname() {
        return getEntity().getLastname();
    }

    public String getEmail() {
        return getEntity().getEmail();
    }

    public boolean isEnabled() {
        return getEntity().isEnabled();
    }

    public String getAddress() {
        return getEntity().getAddress();
    }

    public String getPhone() {
        return getEntity().getPhone();
    }
}
