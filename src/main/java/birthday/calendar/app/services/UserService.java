package birthday.calendar.app.services;

import birthday.calendar.app.dtos.UserDTO;
import birthday.calendar.app.models.User;
import birthday.calendar.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Find user by id
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Create a new user
    public void saveUser(UserDTO.CreateUser data) {
        User userData = new User();
        userData.setUsername(data.getUsername());
        userData.setFirstName(data.getFirstName());
        userData.setLastName(data.getLastName());
        userData.setEmail(data.getEmail());
        userData.setIsEmailVerified(data.getIsEmailVerified());
        userData.setRole(data.getRole());
        userData.setPassword(data.getPassword());

        userRepository.save(userData);
    }
}
