package com.example.questifysharedapi.dto;

import com.example.questifysharedapi.model.User;

public record UserRecordDTO(String name,
                            String email,
                            String password,
                            User.Role type) {
}
