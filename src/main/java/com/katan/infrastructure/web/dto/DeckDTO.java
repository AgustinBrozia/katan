package com.katan.infrastructure.web.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeckDTO {
  private Long id;
  private String name;
  private List<CardDTO> cards;
  private MaterialDTO cost;
}

