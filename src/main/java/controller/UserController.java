
package controller;

import model.User;
import service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        User user = userService.registerUser(newUser);
        return ResponseEntity.ok(user);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
//        try {
//            User user = userService.loginUser(email, password);
//            return ResponseEntity.ok(user);
//        } catch (IllegalArgumentException | IllegalStateException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
}
