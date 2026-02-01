package controller;

import model.User;
import service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    @GetMapping
     public ResponseEntity<List<User>> getusers(){

        return ResponseEntity.ok(userService.fetchusers());
     }
    @GetMapping("/{id}")
    public ResponseEntity<User> getAuser(@PathVariable Long id){
//User user=userService.fetchAuser(id);
//        if(user==null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(user);
        return userService.fetchAuser(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<String> createusers(@RequestBody User user){
        userService.addusers(user);
        return ResponseEntity.ok("succesfully added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatedUser(@PathVariable Long id,@RequestBody User updatedUser){
        boolean updated=userService.updateUser(id,updatedUser);
        if(updated)
            return ResponseEntity.ok("succesfully added");
        return ResponseEntity.notFound().build();
    }
}
