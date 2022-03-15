package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.model.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CharacterItemService {
    private final ItemService itemService;
    private final CharacterService characterService;

    public void addRelation(Character character, Item item){
        List<Item> itemList = character.getItemsList();
        if (!itemList.contains(item)) {
            assert !item.getOwnersList().contains(character);
            itemList.add(item);
            item.getOwnersList().add(character);
            characterService.save(character);
            itemService.save(item);
        }
    }

    public void removeRelation(Character character, Item item){
        List<Item> itemsList = character.getItemsList();
        if (itemsList.contains(item)) {
            assert item.getOwnersList().contains(character);
            itemsList.remove(item);
            item.getOwnersList().remove(character);
            itemService.save(item);
            characterService.save(character);
        }
    }

}
