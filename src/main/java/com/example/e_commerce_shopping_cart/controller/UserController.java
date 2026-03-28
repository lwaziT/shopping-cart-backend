package com.example.e_commerce_shopping_cart.controller;

import com.example.e_commerce_shopping_cart.dto.UserDto;
import com.example.e_commerce_shopping_cart.exceptions.AlreadyExistsException;
import com.example.e_commerce_shopping_cart.exceptions.ResourceNotFoundException;
import com.example.e_commerce_shopping_cart.model.User;
import com.example.e_commerce_shopping_cart.request.CreateUserRequest;
import com.example.e_commerce_shopping_cart.request.UserUpdateRequest;
import com.example.e_commerce_shopping_cart.response.ApiResponse;
import com.example.e_commerce_shopping_cart.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/users")
public class UserController {
        private final IUserService userService;

        @GetMapping("/{userId}")
        public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
            try {
                User user = userService.getUserById(userId);
                UserDto userDto = userService.convertUserToDto(user);
                return ResponseEntity.ok(new ApiResponse("Success", userDto));
            } catch (Exception e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
            }
        }

        @PostMapping("/user")
        public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
            try {
                User user = userService.createUser(request);
                UserDto userDto = userService.convertUserToDto(user);
                return ResponseEntity.ok(new ApiResponse("Create User Success!", userDto));
            } catch (AlreadyExistsException e) {
                return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(),null));
            }
        }

        @PutMapping("/{userId}")
        public ResponseEntity<ApiResponse> updateUser(@RequestBody UserUpdateRequest request, @PathVariable Long userId) {
            try {
                User user = userService.updateUser(request, userId);
                UserDto userDto = userService.convertUserToDto(user);
                return ResponseEntity.ok(new ApiResponse("Update User Success!", userDto));
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
            }
        }

        @DeleteMapping("/{userId}")
        public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
            try {
                userService.deleteUser(userId);
                return ResponseEntity.ok(new ApiResponse("Delete User Success!", userId));
            } catch (ResourceNotFoundException e) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
            }
        }
}
























