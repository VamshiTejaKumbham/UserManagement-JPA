package com.user.usermanagementapi.controller;

import com.user.usermanagementapi.model.User;

import com.user.usermanagementapi.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

//mark this class as a REST controller, handling incoming HTTP requests
@RestController
@RequestMapping("/api/users")  //Base path for all the endpoints in this controller
public class UserController {
    @Autowired //inject the UserRepository dependency
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<List<User>> createUsers(@Valid @RequestBody List<User> users){
    	List<User> savedUsers = userRepository.saveAll(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsers);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll(); // Retrieves all the users from the db
    }
    
    @GetMapping("/page")
    public Page<User> getUsers(Pageable pageable){
    	return userRepository.findAll(pageable);
    }
    
    @GetMapping("/{id}")   //...api/users/{id}
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user=userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,@Valid @RequestBody User userDetails){
        return userRepository.findById(id).map(existingUser ->{
            existingUser.setName(userDetails.getName());
            existingUser.setEmail(userDetails.getEmail());
            User updatedUser= userRepository.save(existingUser);

            return ResponseEntity.ok(updatedUser);
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id); //Delete the user
            return ResponseEntity.noContent().build(); //return 204 No content
        }else{
            return ResponseEntity.notFound().build(); //if user not found return 404 not found
        }
    }




}
