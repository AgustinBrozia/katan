package com.katan.application.service;

import com.katan.domain.model.Card;
import com.katan.domain.model.Deck;
import com.katan.domain.model.Material;
import com.katan.domain.repository.CardRepository;
import com.katan.domain.repository.DeckRepository;
import com.katan.infrastructure.web.dto.CardDTO;
import com.katan.infrastructure.web.dto.DeckDTO;
import com.katan.infrastructure.web.dto.MaterialDTO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeckService {

  @Autowired
  private DeckRepository deckRepository;

  @Autowired
  private CardRepository cardRepository;

  public List<DeckDTO> getAllDecks() {
    return deckRepository.findAll().stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }

  public Optional<DeckDTO> getDeckById(Long id) {
    return deckRepository.findById(id)
        .map(this::toDTO);
  }

  public DeckDTO createDeck(DeckDTO deckDTO) {
    Deck deck = toEntity(deckDTO);
    Deck savedDeck = deckRepository.save(deck);
    return toDTO(savedDeck);
  }

  private DeckDTO toDTO(Deck deck) {
    return DeckDTO.builder()
        .id(deck.getId())
        .name(deck.getName())
        .cards(deck.getCards().stream().map(this::toCardDTO).collect(Collectors.toList()))
        .cost(toMaterialDTO(deck.getCost()))
        .build();
  }

  private CardDTO toCardDTO(Card card) {
    return CardDTO.builder()
        .id(card.getId())
        .title(card.getTitle())
        .text(card.getText())
        .imageUrl(card.getImageUrl())
        .deckId(card.getDeck().getId())
        .build();
  }

  private MaterialDTO toMaterialDTO(Material material) {
    return MaterialDTO.builder()
        .id(material.getId())
        .wheat(material.getWheat())
        .sheep(material.getSheep())
        .brick(material.getBrick())
        .stone(material.getStone())
        .wood(material.getWood())
        .build();
  }


  private Deck toEntity(DeckDTO deckDTO) {
    return Deck.builder()
        .id(deckDTO.getId())
        .name(deckDTO.getName())
        .cards(deckDTO.getCards().stream().map(this::toCardEntity).collect(Collectors.toList()))
        .cost(toMaterialEntity(deckDTO.getCost()))
        .build();
  }

  private Card toCardEntity(CardDTO cardDTO) {
    return Card.builder()
        .id(cardDTO.getId())
        .title(cardDTO.getTitle())
        .text(cardDTO.getText())
        .imageUrl(cardDTO.getImageUrl())
        .deck(Deck.builder().id(cardDTO.getDeckId()).build())
        .build();
  }

  private Material toMaterialEntity(MaterialDTO materialDTO) {
    return Material.builder()
        .id(materialDTO.getId())
        .wheat(materialDTO.getWheat())
        .sheep(materialDTO.getSheep())
        .brick(materialDTO.getBrick())
        .stone(materialDTO.getStone())
        .wood(materialDTO.getWood())
        .build();
  }

}