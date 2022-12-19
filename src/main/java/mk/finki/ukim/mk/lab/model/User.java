package mk.finki.ukim.mk.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "Balloon_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Convert(converter = UserFullnameConverter.class)
    private UserFullname userFullname;

    private String password;

    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<ShoppingCart> carts;

    public User(String username,String name,String surname,String password, LocalDate dateOfBirth) {
        this.username = username;
        this.userFullname =  new UserFullname(name,surname);
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public User() {}

}
