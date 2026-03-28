package com.example.e_commerce_shopping_cart.service.user;

import com.example.e_commerce_shopping_cart.dto.UserDto;
import com.example.e_commerce_shopping_cart.model.User;
import com.example.e_commerce_shopping_cart.request.CreateUserRequest;
import com.example.e_commerce_shopping_cart.request.UserUpdateRequest;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);
}
