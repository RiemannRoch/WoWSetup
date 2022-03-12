package com.riemannroch.wowsetup.view.character;

import com.riemannroch.wowsetup.model.Character;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CharacterView {
    private long id;
    private String name;

    public CharacterView(Character character) {
        this.id = character.getIdCharacter();
        this.name = character.getName();
    }

    public static List<CharacterView> listOf(List<Character> characterList){
        List<CharacterView> characterViewList = new ArrayList<>();
        for (Character character : characterList){
            characterViewList.add(new CharacterView(character));
        }
        return characterViewList;
    }
}
