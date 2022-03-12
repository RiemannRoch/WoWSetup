package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.repository.CharacterRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {
    final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> findAll(){
        return this.characterRepository.findAll();
    }

    @Transactional
    public Character save(Character character){
        return this.characterRepository.save(character);
    }

    public Optional<Character> findByName(String name) {
        return this.characterRepository.findByName(name);
    }

    @Transactional
    public void deleteByName(String name) {
        characterRepository.deleteByName(name);
    }

    public void delete(Character character) {
        characterRepository.delete(character);
    }
}
