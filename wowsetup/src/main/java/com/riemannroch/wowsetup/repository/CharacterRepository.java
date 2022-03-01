package com.riemannroch.wowsetup.repository;

import com.riemannroch.wowsetup.model.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacterRepository extends JpaRepository<CharacterModel,Long> {
    Optional<CharacterModel> findByName(String name);

    void deleteByName(String name);
}
