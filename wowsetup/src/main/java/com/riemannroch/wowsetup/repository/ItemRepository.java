package com.riemannroch.wowsetup.repository;

import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.model.SlotEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findBySlotEnum(SlotEnum slotEnum);
}
