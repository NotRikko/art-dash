package com.artDash.artDash.dto;

import com.artDash.artDash.model.Role;

public record UserDTO(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        Role role,
        String bio,
        String profilePicUrl
) {}
