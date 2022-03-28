package com.riemannroch.wowsetup.view.itemequivalencepoints;

import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.SlotEnum;
import com.riemannroch.wowsetup.view.item.ItemView;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemEquivalencePointsView {
    private String name;
    private SlotEnum slot;
    private double equivalencePoints;

    public ItemEquivalencePointsView(ItemEquivalencePoints itemEquivalencePoints) {
        this.name = itemEquivalencePoints.getItem().getName();
        this.slot = itemEquivalencePoints.getItem().getSlotEnum();
        this.equivalencePoints = itemEquivalencePoints.getEquivalencePoints();
    }

    public static List<ItemEquivalencePointsView> listOf(List<ItemEquivalencePoints> itemEquivalencePointsList) {
        List<ItemEquivalencePointsView> itemEquivalencePointsViewList = new ArrayList<>();
        for (ItemEquivalencePoints itemEquivalencePoints : itemEquivalencePointsList) {
            itemEquivalencePointsViewList.add(new ItemEquivalencePointsView(itemEquivalencePoints));
        }
        return itemEquivalencePointsViewList;
    }
}
