package com.katan.infrastructure.web.controller;

import com.katan.application.service.RoomService;
import com.katan.infrastructure.web.dto.RoomDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

  @Autowired
  private RoomService roomService;

  @GetMapping
  public List<RoomDTO> getAllRooms() {
    return roomService.getAllRooms();
  }

  @GetMapping("/{id}")
  public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
    return roomService.getRoomById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public RoomDTO createRoom(@RequestBody RoomDTO roomDTO) {
    return roomService.createRoom(roomDTO);
  }
}





