package birthday.calendar.app.controllers;

import birthday.calendar.app.dtos.ErrorResponse;
import birthday.calendar.app.dtos.SuccessResponse;
import birthday.calendar.app.dtos.UserDTO;
import birthday.calendar.app.models.User;
import birthday.calendar.app.services.UserService;
import birthday.calendar.app.utilities.Helpers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    // Register an account
    @PostMapping("register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO.CreateUserRequest req) {

        //  Check existing email
        Optional<User> availableUser = userService.findByEmail(req.getEmail());
        if (availableUser.isPresent()) {
            Map<String, String[]> errors = new HashMap<>();
            errors.put("email", new String[]{"This email already using."});
            ErrorResponse<Map<String, String[]>> errorResponse = new ErrorResponse<>("Found existing data.", errors);
            return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
        }

        // Generated unique username
        String uniqueUsername = Helpers.generateUniqueUsername(req.getFirstName(), req.getLastName());

        // Generated hash password
        String hashPassword = Helpers.generateEncryptedPassword(req.getPassword());

        // Store new user to DB
        UserDTO.CreateUser userDocuments = new UserDTO.CreateUser();
        userDocuments.setUsername(uniqueUsername);
        userDocuments.setFirstName(req.getFirstName());
        userDocuments.setLastName(req.getLastName());
        userDocuments.setEmail(req.getEmail());
        userDocuments.setIsEmailVerified(false);
        userDocuments.setRole(1L);
        userDocuments.setPassword(hashPassword);
        userService.saveUser(userDocuments);

        return ResponseEntity.ok(new SuccessResponse<>(true, "Account created successfully.", null));
    }

    // Login to an account
    @PostMapping("login")
    public ResponseEntity<?> login(@Valid @RequestBody UserDTO.LoginUserRequest req) {
        //  Check existing account using email
        Optional<User> availableAccount = userService.findByEmail(req.getEmail());
        if (availableAccount.isEmpty()) {
            Map<String, String[]> errors = new HashMap<>();
            errors.put("credentials", new String[]{"Invalid login credentials."});
            ErrorResponse<Map<String, String[]>> errorResponse = new ErrorResponse<>("invalid data.", errors);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        User user = availableAccount.get();

        // Validate password with existing password
        boolean isPasswordMatches = Helpers.verifyPassword(req.getPassword(), user.getPassword());
        if (!isPasswordMatches) {
            Map<String, String[]> errors = new HashMap<>();
            errors.put("credentials", new String[]{"Invalid login credentials."});
            ErrorResponse<Map<String, String[]>> errorResponse = new ErrorResponse<>("invalid data.", errors);
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(new SuccessResponse<>(true, "Login request.", "JWT token will be goes here."));
    }
}
