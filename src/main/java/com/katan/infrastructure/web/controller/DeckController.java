package com.katan.infrastructure.web.controller;

import com.katan.application.service.DeckService;
import com.katan.infrastructure.web.dto.DeckDTO;
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
@RequestMapping("/api/decks")
public class DeckController {

  @Autowired
  private DeckService deckService;

  @GetMapping
  public List<DeckDTO> getAllDecks() {
    return deckService.getAllDecks();
  }

  @GetMapping("/{id}")
  public ResponseEntity<DeckDTO> getDeckById(@PathVariable Long id) {
    return deckService.getDeckById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public DeckDTO createDeck(@RequestBody DeckDTO deckDTO) {
    return deckService.createDeck(deckDTO);
  }
}
