package com.riemannroch.wowsetup.view.character;

import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.view.item.ItemView;
import lombok.Data;

import java.util.List;

@Data
public class CharacterWithItemsView {
    private long id;
    private String name;
    private List<ItemView> ItemsList;

    public CharacterWithItemsView(Character character) {
        this.id = character.getIdCharacter();
        this.name = character.getName();
        this.ItemsList = ItemView.listOf(character.getItemsList());
    }
}
