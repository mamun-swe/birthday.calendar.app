package birthday.calendar.app.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class UserDTO {
    @Getter
    @Setter
    public static class User {
        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private Boolean isEmailVerified;
        private Long role;
        private String password;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

    @Getter
    @Setter
    public static class LoginUserRequest {
        @NotNull(message = "Email address is required.")
        @NotBlank(message = "Email address is required.")
        @Email(message = "Email address should be valid.")
        private String email;

        @NotNull(message = "Password is required.")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        private String password;
    }

    @Getter
    @Setter
    public static class CreateUserRequest {
        @NotNull(message = "First name is required.")
        private String firstName;

        @NotNull(message = "Last name is required.")
        private String lastName;

        @NotNull(message = "Email address is required.")
        @NotBlank(message = "Email address is required.")
        @Email(message = "Email address should be valid.")
        private String email;

        @NotNull(message = "Password is required.")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        private String password;
    }

    @Getter
    @Setter
    public static class CreateUser {
        private String username;
        private String firstName;
        private String lastName;
        private String email;
        private Boolean isEmailVerified;
        private Long role;
        private String password;
    }
}
