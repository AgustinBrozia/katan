package com.katan.application.service;

import com.katan.domain.model.Room;
import com.katan.domain.model.User;
import com.katan.domain.repository.UserRepository;
import com.katan.infrastructure.web.dto.UserDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired private UserRepository userRepository;

  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
  }

  public Optional<UserDTO> getUserById(Long id) {
    return userRepository.findById(id).map(this::toDTO);
  }

  public UserDTO createUser(UserDTO userDTO) {
    User user = toEntity(userDTO);
    User savedUser = userRepository.save(user);
    return toDTO(savedUser);
  }

  private UserDTO toDTO(User user) {
    return UserDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .password(user.getPassword())
        .hostedRoomIds(user.getHostedRooms().stream().map(Room::getId).collect(Collectors.toList()))
        .build();
  }

  private User toEntity(UserDTO userDTO) {
    return User.builder()
        .id(userDTO.getId())
        .username(userDTO.getUsername())
        .password(userDTO.getPassword())
        .build();
  }
}
