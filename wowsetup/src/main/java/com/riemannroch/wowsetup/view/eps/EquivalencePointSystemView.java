package com.riemannroch.wowsetup.view.eps;

import com.riemannroch.wowsetup.model.EquivalencePointSystemModel;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EquivalencePointSystemView {
    private long id;
    private String name;

    public EquivalencePointSystemView(EquivalencePointSystemModel equivalencePointSystemModel) {
        this.id = equivalencePointSystemModel.getIdEquivalencePointSystem();
        this.name = equivalencePointSystemModel.getName();
    }

    public static List<EquivalencePointSystemView> listOf(List<EquivalencePointSystemModel> equivalencePointSystemModelList) {
        List<EquivalencePointSystemView> equivalencePointSystemViewList = new ArrayList<>();
        for (EquivalencePointSystemModel equivalencePointSystemModel : equivalencePointSystemModelList) {
            equivalencePointSystemViewList.add(new EquivalencePointSystemView(equivalencePointSystemModel));
        }
        return equivalencePointSystemViewList;
    }
}
