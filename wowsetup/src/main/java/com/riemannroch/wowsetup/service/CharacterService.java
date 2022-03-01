package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.CharacterModel;
import com.riemannroch.wowsetup.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<CharacterModel> findAll(){
        return this.characterRepository.findAll();
    }

    public CharacterModel save(CharacterModel characterModel){
        return this.characterRepository.save(characterModel);
    }

    public Optional<CharacterModel> findByName(String name) {
        return this.characterRepository.findByName(name);
    }

    public void deleteByName(String name) {
        characterRepository.deleteByName(name);
    }

    public void delete(CharacterModel characterModel) {
        characterRepository.delete(characterModel);
    }
}
