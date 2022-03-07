package com.riemannroch.wowsetup.view.item;

import com.riemannroch.wowsetup.model.ItemModel;
import com.riemannroch.wowsetup.model.SlotEnum;

import java.util.ArrayList;
import java.util.List;

public class ItemView {
    private long id;
    private String name;
    private SlotEnum slotEnum;

    public ItemView(ItemModel itemModel) {
        this.id = itemModel.getIdItem();
        this.name = itemModel.getName();
        this.slotEnum = itemModel.getSlot();
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

    public SlotEnum getSlot() {
        return slotEnum;
    }

    public void setSlot(SlotEnum slotEnum) {
        this.slotEnum = slotEnum;
    }
}
