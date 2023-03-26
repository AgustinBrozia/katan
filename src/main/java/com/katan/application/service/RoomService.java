package com.katan.application.service;

import com.katan.domain.model.Deck;
import com.katan.domain.model.Room;
import com.katan.domain.model.User;
import com.katan.domain.repository.RoomRepository;
import com.katan.infrastructure.web.dto.RoomDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

  @Autowired
  private RoomRepository roomRepository;

  public List<RoomDTO> getAllRooms() {
    return roomRepository.findAll().stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }

  public Optional<RoomDTO> getRoomById(Long id) {
    return roomRepository.findById(id)
        .map(this::toDTO);
  }

  public RoomDTO createRoom(RoomDTO roomDTO) {
    Room room = toEntity(roomDTO);
    Room savedRoom = roomRepository.save(room);
    return toDTO(savedRoom);
  }

  private RoomDTO toDTO(Room room) {
    return RoomDTO.builder()
        .id(room.getId())
        .name(room.getName())
        .password(room.getPassword())
        .hostId(room.getHost().getId())
        .deckId(room.getDeck().getId())
        .build();
  }

  private Room toEntity(RoomDTO roomDTO) {
    return Room.builder()
        .id(roomDTO.getId())
        .name(roomDTO.getName())
        .password(roomDTO.getPassword())
        .host(User.builder().id(roomDTO.getHostId()).build())
        .deck(Deck.builder().id(roomDTO.getDeckId()).build())
        .build();
  }
}
