package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.model.SlotEnum;
import com.riemannroch.wowsetup.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll(){
        return this.itemRepository.findAll();
    }

    @Transactional
    public void save(Item item){
        this.itemRepository.save(item);
    }

    public Optional<Item> findById(long id){
        return this.itemRepository.findById(id);
    }

    @Transactional
    public void delete(Item item){
        this.itemRepository.delete(item);
    }

    public List<Item> findBySlot(SlotEnum slotEnum) {
        return this.itemRepository.findBySlotEnum(slotEnum);
    }
}
