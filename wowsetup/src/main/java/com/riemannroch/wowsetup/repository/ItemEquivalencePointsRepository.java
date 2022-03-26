package com.riemannroch.wowsetup.repository;

import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.ItemEquivalencePointsKey;
import com.riemannroch.wowsetup.model.Item;
import org.aspectj.weaver.ast.Literal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemEquivalencePointsRepository extends JpaRepository<ItemEquivalencePoints, ItemEquivalencePointsKey> {
    List<ItemEquivalencePoints> findByItem(Item item);

    List<ItemEquivalencePoints> findByEps(EquivalencePointSystem equivalencePointSystem);

    List<ItemEquivalencePoints> findByEps(EquivalencePointSystem equivalencePointSystem, Sort sort);
}
