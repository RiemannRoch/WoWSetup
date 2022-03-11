package com.riemannroch.wowsetup.view.character;

import com.riemannroch.wowsetup.model.CharacterModel;
import com.riemannroch.wowsetup.view.item.ItemView;
import lombok.Data;

import java.util.List;

@Data
public class CharacterWithItemsView {
    private long id;
    private String name;
    private List<ItemView> ItemsList;

    public CharacterWithItemsView(CharacterModel characterModel) {
        this.id = characterModel.getIdCharacter();
        this.name = characterModel.getName();
        this.ItemsList = ItemView.listOf(characterModel.getItemsList());
    }
}
