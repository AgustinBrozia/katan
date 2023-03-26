package com.katan.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialDTO {
  private Long id;
  private int wood;
  private int brick;
  private int wheat;
  private int sheep;
  private int stone;
}
