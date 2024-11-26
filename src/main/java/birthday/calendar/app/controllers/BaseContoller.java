package birthday.calendar.app.controllers;

import birthday.calendar.app.dtos.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseContoller {
    // Welcome
    @GetMapping
    public ResponseEntity<?> register() {
        return ResponseEntity.ok(new SuccessResponse<>(true, "Welcome to birthday calendar.", null));
    }
}
