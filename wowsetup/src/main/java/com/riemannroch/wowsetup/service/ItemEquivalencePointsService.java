package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.EquivalencePointSystemModel;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.ItemEquivalencePointsKey;
import com.riemannroch.wowsetup.model.ItemModel;
import com.riemannroch.wowsetup.repository.ItemEquivalencePointsRepository;
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

    @Transactional
    public void save(ItemEquivalencePoints itemEquivalencePoints) {
        this.itemEquivalencePointsRepository.save(itemEquivalencePoints);
    }

    @Transactional
    public void deleteById(ItemEquivalencePointsKey itemEquivalencePointsKey) {
        this.itemEquivalencePointsRepository.deleteById(itemEquivalencePointsKey);
    }

    public List<ItemEquivalencePoints> findByItem(ItemModel itemModel) {
        return this.itemEquivalencePointsRepository.findByItem(itemModel);
    }
    @Transactional
    public void delete(ItemEquivalencePoints itemEquivalencePoints) {
        this.itemEquivalencePointsRepository.delete(itemEquivalencePoints);
    }

    public List<ItemEquivalencePoints> findByEps(EquivalencePointSystemModel equivalencePointSystemModel) {
        return itemEquivalencePointsRepository.findByEps(equivalencePointSystemModel);
    }
}
