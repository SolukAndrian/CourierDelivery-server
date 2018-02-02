package ua.lviv.courierdelivery.model.dto.impl;

/**
 * Created by Apple on 10.01.2018.
 */
public class UserDTO {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private boolean enabled;
    private String address;
    private String phone;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}
