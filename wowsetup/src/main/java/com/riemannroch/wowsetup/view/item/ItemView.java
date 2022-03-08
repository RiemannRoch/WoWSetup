package com.riemannroch.wowsetup.view.item;

import com.riemannroch.wowsetup.model.ItemModel;
import com.riemannroch.wowsetup.model.SlotEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemView {
    private long id;
    private String name;
    private SlotEnum slotEnum;

    public ItemView(ItemModel itemModel) {
        this.id = itemModel.getIdItem();
        this.name = itemModel.getName();
        this.slotEnum = itemModel.getSlotEnum();
    }

    public static List<ItemView> listOf(List<ItemModel> itemModelList) {
        List<ItemView> itemViewList = new ArrayList<>();
        for (ItemModel itemModel: itemModelList){
            itemViewList.add(new ItemView(itemModel));
        }
        return itemViewList;
    }
}
