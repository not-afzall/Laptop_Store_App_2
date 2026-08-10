package com.afz.lapstore.service;

import com.afz.lapstore.dto.*;
import com.afz.lapstore.entity.User;
import com.afz.lapstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO register(UserRequestDTO requestDTO) {

        User user = convertToEntity(requestDTO);

        user = userRepository.save(user);

        return convertToResponseDTO(user);
    }

    public UserResponseDTO login(UserRequestDTO requestDTO) {

        User user = userRepository.findByEmail(requestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(requestDTO.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        return convertToResponseDTO(user);
    }

    private User convertToEntity(UserRequestDTO requestDTO) {

        User user = new User();

        user.setName(requestDTO.getName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        user.setRole(requestDTO.getRole());

        return user;
    }

    private UserResponseDTO convertToResponseDTO(User user) {

        UserResponseDTO responseDTO = new UserResponseDTO();

        responseDTO.setUserId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setRole(user.getRole());

        return responseDTO;
    }
}
