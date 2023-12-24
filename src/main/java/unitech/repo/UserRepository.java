package unitech.repo;

import unitech.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByFin(String fin);
    boolean existsByFin(String fin);

}
