package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.Character;
import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.SlotEnum;
import com.riemannroch.wowsetup.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final EquivalencePointSystemService equivalencePointSystemService;
    private final ItemEquivalencePointsService itemEquivalencePointsService;
    private final CharacterService characterService;

    public List<Item> findAll(){
        return this.itemRepository.findAll();
    }

    @Transactional
    public void save(Item item){
        this.itemRepository.save(item);
        for (EquivalencePointSystem eps : equivalencePointSystemService.findAll()) {
            itemEquivalencePointsService.save(new ItemEquivalencePoints(item, eps));
        }
    }

    public Optional<Item> findById(long id){
        return this.itemRepository.findById(id);
    }

    @Transactional
    public void delete(Item item){
        for (Character character: item.getOwnersList()){
            character.getItemsList().remove(item);
            characterService.save(character);
        }
        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsService.findByItem(item)) {
            itemEquivalencePointsService.delete(itemEquivalencePoints);
        }
        this.itemRepository.delete(item);
    }

    public List<Item> findBySlot(SlotEnum slotEnum) {
        return this.itemRepository.findBySlotEnum(slotEnum);
    }
}
