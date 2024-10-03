package com.example.questifysharedapi.service;

import com.example.questifysharedapi.dto.UserRecordDTO;
import com.example.questifysharedapi.model.User;
import com.example.questifysharedapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User saveUser(UserRecordDTO userRecordDTO){
        User user = new User();
        user.setName(userRecordDTO.name());
        user.setEmail(userRecordDTO.email());
        user.setPassword(user.getPassword());
        user.setType(user.getType());
        return null;
    }
}
