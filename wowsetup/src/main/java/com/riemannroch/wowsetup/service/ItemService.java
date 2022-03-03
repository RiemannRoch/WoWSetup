package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.ItemModel;
import com.riemannroch.wowsetup.model.Slot;
import com.riemannroch.wowsetup.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemModel> findAll(){
        return this.itemRepository.findAll();
    }

    public ItemModel save(ItemModel itemModel){
        return this.itemRepository.save(itemModel);
    }

    public Optional<ItemModel> findById(long id){
        return this.itemRepository.findById(id);
    }

    public void delete(ItemModel itemModel){
        this.itemRepository.delete(itemModel);
    }

    public List<ItemModel> findBySlot(Slot slot) {
        return this.itemRepository.findBySlot(slot);
    }
}
