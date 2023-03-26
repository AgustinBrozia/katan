package com.katan.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {
  private Long id;
  private String name;
  private String password;
  private Long hostId;
  private Long deckId;
}
