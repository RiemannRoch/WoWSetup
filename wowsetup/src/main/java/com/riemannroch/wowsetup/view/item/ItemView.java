package com.riemannroch.wowsetup.view.item;

import com.riemannroch.wowsetup.model.ItemEquivalencePoints;
import com.riemannroch.wowsetup.model.ItemModel;
import com.riemannroch.wowsetup.model.Slot;

import java.util.ArrayList;
import java.util.List;

public class ItemView {
    private long id;
    private String name;
    private Slot slot;

    public ItemView(ItemModel itemModel) {
        this.id = itemModel.getIdItem();
        this.name = itemModel.getName();
        this.slot = itemModel.getSlot();
    }

    public static List<ItemView> listOf(List<ItemModel> itemModelList) {
        List<ItemView> itemViewList = new ArrayList<>();
        for (ItemModel itemModel: itemModelList){
            itemViewList.add(new ItemView(itemModel));
        }
        return itemViewList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
