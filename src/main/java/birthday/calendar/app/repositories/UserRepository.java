package birthday.calendar.app.repositories;

import birthday.calendar.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method to find a user by email
    Optional<User> findByEmail(String email);
}