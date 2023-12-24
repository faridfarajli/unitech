package unitech.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import unitech.model.CreditCard;

import java.util.List;

public interface CCRepository extends JpaRepository<CreditCard,Long> {
    Boolean existsByCcNumber(String cc);
    @Query("SELECT c FROM CreditCard c WHERE c.activeAccount = true")
    List<CreditCard> getAllActiveAccount();
}
