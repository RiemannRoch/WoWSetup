package com.riemannroch.wowsetup.service;

import com.riemannroch.wowsetup.model.EquivalencePointSystemModel;
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

    public List<EquivalencePointSystemModel> findAll(){
        return equivalencePointSystemRepository.findAll();
    }

    @Transactional
    public EquivalencePointSystemModel save(EquivalencePointSystemModel equivalencePointSystemModel) {
        return equivalencePointSystemRepository.save(equivalencePointSystemModel);
    }

    public Optional<EquivalencePointSystemModel> findById(long idEquivalencePointSystem) {
        return equivalencePointSystemRepository.findById(idEquivalencePointSystem);
    }

    @Transactional
    public void deleteById(long idEquivalencePointSystem) {
        equivalencePointSystemRepository.deleteById(idEquivalencePointSystem);
    }

    @Transactional
    public void delete(EquivalencePointSystemModel equivalencePointSystemModel) {
        equivalencePointSystemRepository.delete(equivalencePointSystemModel);
    }
}
