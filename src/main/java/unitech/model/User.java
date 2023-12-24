package unitech.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @NotEmpty(message = "User FIN can not be empty! Please Enter your FIN")
    private String fin;
    @NotEmpty(message = "User password can not be empty! Please Enter a Valid Password")
    private String password;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role = Role.valueOf("USER");
//    @OneToMany(mappedBy = "user")
//    private List<CreditCard> creditCards;


}

