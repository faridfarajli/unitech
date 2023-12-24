package unitech.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ccNumber;
    private String ccDate;
    private String CVV;
    private Boolean activeAccount;
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
