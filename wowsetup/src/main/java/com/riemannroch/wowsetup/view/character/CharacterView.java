package com.riemannroch.wowsetup.view.character;

import com.riemannroch.wowsetup.model.CharacterModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
public class CharacterView {
    private long id;
    private String name;

    public CharacterView(CharacterModel characterModel) {
        this.id = characterModel.getIdCharacter();
        this.name = characterModel.getName();
    }

    public static List<CharacterView> listOf(List<CharacterModel> characterModelList){
        List<CharacterView> characterViewList = new ArrayList<>();
        for (CharacterModel characterModel: characterModelList){
            characterViewList.add(new CharacterView(characterModel));
        }
        return characterViewList;
    }
}
