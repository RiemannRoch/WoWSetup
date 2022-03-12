package com.riemannroch.wowsetup.repository;

import com.riemannroch.wowsetup.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterRepository extends JpaRepository<Character,Long> {
    Optional<Character> findByName(String name);

    void deleteByName(String name);
}
