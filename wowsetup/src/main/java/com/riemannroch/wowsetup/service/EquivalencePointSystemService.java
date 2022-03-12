package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import com.riemannroch.wowsetup.repository.EquivalencePointSystemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EquivalencePointSystemService {
    final EquivalencePointSystemRepository equivalencePointSystemRepository;

    public EquivalencePointSystemService(EquivalencePointSystemRepository equivalencePointSystemRepository) {
        this.equivalencePointSystemRepository = equivalencePointSystemRepository;
    }

    public List<EquivalencePointSystem> findAll(){
        return equivalencePointSystemRepository.findAll();
    }

    @Transactional
    public EquivalencePointSystem save(EquivalencePointSystem equivalencePointSystem) {
        return equivalencePointSystemRepository.save(equivalencePointSystem);
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
        equivalencePointSystemRepository.delete(equivalencePointSystem);
    }
}
