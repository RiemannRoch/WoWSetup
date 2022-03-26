package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CharacterService {
    final CharacterRepository characterRepository;

    public List<Character> findAll(){
        return this.characterRepository.findAll();
    }

    public Optional<Character> findByName(String name) {
        return this.characterRepository.findByName(name);
    }

    @Transactional
    public Character save(Character character){
        return this.characterRepository.save(character);
    }

    @Transactional
    public void delete(Character character,
                       ItemService itemService,
                       EquivalencePointSystemService equivalencePointSystemService,
                       ItemEquivalencePointsService itemEquivalencePointsService) {
        for (Item item : character.getItemsList()) {
            item.getOwnersList().remove(character);
            itemService.update(item,equivalencePointSystemService, itemEquivalencePointsService);
        }
        characterRepository.delete(character);
    }
}
