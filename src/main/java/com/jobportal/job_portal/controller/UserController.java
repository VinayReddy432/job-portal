package com.jobportal.job_portal.controller;


import com.jobportal.job_portal.config.JwtUtil;
import com.jobportal.job_portal.dto.LoginRequest;
import com.jobportal.job_portal.entity.User;
import com.jobportal.job_portal.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
   private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
       User saveduser= userService.registerUser(user);
       return  ResponseEntity.status(HttpStatus.CREATED).body(saveduser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .map(user->ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest loginRequest){
        User user=userService.loginUser(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        String token= jwtUtil.generateToken(user.getEmail());
        return  ResponseEntity.ok(token);
    }

    // automatically injects UserService here
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }


}
