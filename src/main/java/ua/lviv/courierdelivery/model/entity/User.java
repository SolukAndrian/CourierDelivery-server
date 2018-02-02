package ua.lviv.courierdelivery.model.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.lviv.courierdelivery.model.base.BaseEntity;
import ua.lviv.courierdelivery.model.dto.impl.UserDTO;
import ua.lviv.courierdelivery.model.enums.AuthorityName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @Column(name = "EMAIL", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "address")
    @Size(min = 4, max = 50)
    private String address;

    @Column(name = "phone", unique = true)
    @Size(min = 7, max = 20)
    private String phone;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "id")})
    private List<Authority> authorities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Order> orders;

    public User() {
    }

    public User(long id) {
        super();
    }

//    public User(User user) {
//        super();
//        this.username = user.getUsername();
//        this.email = user.getEmail();
//        this.password = user.getPassword();
//        this.firstname = user.getFirstname();
//        this.lastname = user.getLastname();
//        this.firstname = user.getFirstname();
//        this.firstname = user.getFirstname();
//    }
//
//    public User(String email, String username, String password, AuthorityName authorityName) {
//        this.email = email;
//        this.username = username;
//        this.password = new BCryptPasswordEncoder().encode(password);
//        this.role = authorityName;
//    }
//
    public User(String email, String username, String password, boolean enabled, String firstName, String lastName, String address) {
        this.email = email;
        this.username = username;
        this.password = new BCryptPasswordEncoder().encode(password);
        this.enabled = enabled;
        this.firstname = firstName;
        this.lastname = lastName;
        this.address = address;
    }


    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static User toEntity(UserDTO userDto) {
        return new User(userDto.getEmail(), userDto.getUsername(),
                userDto.getPassword(), userDto.isEnabled(), userDto.getFirstname(), userDto.getLastname(), userDto.getAddress());
    }

}
