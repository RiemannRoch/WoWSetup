package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.ItemModel;
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

    public List<ItemModel> findAll(){
        return this.itemRepository.findAll();
    }

    @Transactional
    public ItemModel save(ItemModel itemModel){
        return this.itemRepository.save(itemModel);
    }

    public Optional<ItemModel> findById(long id){
        return this.itemRepository.findById(id);
    }

    @Transactional
    public void delete(ItemModel itemModel){
        this.itemRepository.delete(itemModel);
    }

    public List<ItemModel> findBySlot(SlotEnum slotEnum) {
        return this.itemRepository.findBySlot(slotEnum);
    }
}
