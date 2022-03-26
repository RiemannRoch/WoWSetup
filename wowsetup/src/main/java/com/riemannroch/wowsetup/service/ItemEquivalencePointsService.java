package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.ItemEquivalencePointsKey;
import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.repository.ItemEquivalencePointsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ItemEquivalencePointsService {
    final ItemEquivalencePointsRepository itemEquivalencePointsRepository;

    public ItemEquivalencePointsService(ItemEquivalencePointsRepository itemEquivalencePointsRepository) {
        this.itemEquivalencePointsRepository = itemEquivalencePointsRepository;
    }

    public Optional<ItemEquivalencePoints> findById(ItemEquivalencePointsKey itemEquivalencePointsKey) {
        return this.itemEquivalencePointsRepository.findById(itemEquivalencePointsKey);
    }

    public List<ItemEquivalencePoints> findByItem(Item item) {
        return this.itemEquivalencePointsRepository.findByItem(item);
    }

    public List<ItemEquivalencePoints> findByEps(EquivalencePointSystem equivalencePointSystem) {
        return itemEquivalencePointsRepository.findByEps(equivalencePointSystem);
    }

    public List<ItemEquivalencePoints> findByEpsAndOrderByEquivalencePoints(EquivalencePointSystem equivalencePointSystem){
        Sort sort = Sort.by("equivalencePoints").descending();
        return itemEquivalencePointsRepository.findByEps(equivalencePointSystem, sort);
    }

    @Transactional
    public void save(ItemEquivalencePoints itemEquivalencePoints) {
        this.itemEquivalencePointsRepository.save(itemEquivalencePoints);
    }

    @Transactional
    public void delete(ItemEquivalencePoints itemEquivalencePoints) {
        this.itemEquivalencePointsRepository.delete(itemEquivalencePoints);
    }
}
