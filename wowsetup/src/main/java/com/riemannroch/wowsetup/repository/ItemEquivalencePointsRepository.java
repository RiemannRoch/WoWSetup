package com.riemannroch.wowsetup.repository;

import com.riemannroch.wowsetup.model.EquivalencePointSystemModel;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.ItemEquivalencePointsKey;
import com.riemannroch.wowsetup.model.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemEquivalencePointsRepository extends JpaRepository<ItemEquivalencePoints, ItemEquivalencePointsKey> {
    List<ItemEquivalencePoints> findByItem(ItemModel itemModel);

    List<ItemEquivalencePoints> findByEps(EquivalencePointSystemModel equivalencePointSystemModel);
}
