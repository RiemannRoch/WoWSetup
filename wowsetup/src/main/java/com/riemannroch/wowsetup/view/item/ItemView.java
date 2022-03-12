package com.riemannroch.wowsetup.view.item;

import com.riemannroch.wowsetup.model.Item;
import com.riemannroch.wowsetup.model.SlotEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemView {
    private long id;
    private String name;
    private SlotEnum slotEnum;

    public ItemView(Item item) {
        this.id = item.getIdItem();
        this.name = item.getName();
        this.slotEnum = item.getSlotEnum();
    }

    public static List<ItemView> listOf(List<Item> itemList) {
        List<ItemView> itemViewList = new ArrayList<>();
        for (Item item : itemList){
            itemViewList.add(new ItemView(item));
        }
        return itemViewList;
    }
}
