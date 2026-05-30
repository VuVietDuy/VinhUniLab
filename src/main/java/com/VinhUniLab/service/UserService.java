package com.VinhUniLab.service;

import com.VinhUniLab.entity.User;
import com.VinhUniLab.model.dto.UserDTO;

import java.util.List;

public interface UserService extends BaseService<User> {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
