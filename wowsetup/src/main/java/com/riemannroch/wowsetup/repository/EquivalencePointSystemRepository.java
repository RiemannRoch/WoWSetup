package com.riemannroch.wowsetup.repository;

import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquivalencePointSystemRepository extends JpaRepository<EquivalencePointSystem,Long> {
}
