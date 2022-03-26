package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.repository.EquivalencePointSystemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EquivalencePointSystemService {
    private final EquivalencePointSystemRepository equivalencePointSystemRepository;
    private final ItemService itemService;
    private final ItemEquivalencePointsService itemEquivalencePointsService;

    public List<EquivalencePointSystem> findAll(){
        return equivalencePointSystemRepository.findAll();
    }

    @Transactional
    public void save(EquivalencePointSystem equivalencePointSystem) {
        equivalencePointSystemRepository.save(equivalencePointSystem);
        for (Item item : itemService.findAll()) {
            itemEquivalencePointsService.save(new ItemEquivalencePoints(item, equivalencePointSystem));
        }
    }

    public Optional<EquivalencePointSystem> findById(long idEquivalencePointSystem) {
        return equivalencePointSystemRepository.findById(idEquivalencePointSystem);
    }

    @Transactional
    public void deleteById(long idEquivalencePointSystem) {
        equivalencePointSystemRepository.deleteById(idEquivalencePointSystem);
    }

    @Transactional
    public void delete(EquivalencePointSystem equivalencePointSystem) {
        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsService.findByEps(equivalencePointSystem)){
            itemEquivalencePointsService.delete(itemEquivalencePoints);
        }
        equivalencePointSystemRepository.delete(equivalencePointSystem);
    }
}
