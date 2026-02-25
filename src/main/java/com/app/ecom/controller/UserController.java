package com.app.ecom.controller;

import com.app.ecom.dto.UserRequest;
import com.app.ecom.dto.UserResponse;
import com.app.ecom.service.UserService;
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
     public ResponseEntity<List<UserResponse>> getusers(){

        return ResponseEntity.ok(userService.fetchusers());
     }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getAuser(@PathVariable Long id){
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
    public ResponseEntity<String> createusers(@RequestBody UserRequest userRequest){
        userService.addusers(userRequest);
        return ResponseEntity.ok("succesfully added");
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updatedUser(@PathVariable Long id,@RequestBody UserRequest updatedUserRequest){
        boolean updated=userService.updateUser(id,updatedUserRequest);
        if(updated)
            return ResponseEntity.ok("succesfully added");
        return ResponseEntity.notFound().build();
    }

}
