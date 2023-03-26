package com.katan.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardDTO {
  private Long id;
  private String title;
  private String text;
  private String imageUrl;
  private Long deckId;
}