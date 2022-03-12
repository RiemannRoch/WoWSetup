package com.riemannroch.wowsetup.view.eps;

import com.riemannroch.wowsetup.model.EquivalencePointSystem;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EquivalencePointSystemView {
    private long id;
    private String name;

    public EquivalencePointSystemView(EquivalencePointSystem equivalencePointSystem) {
        this.id = equivalencePointSystem.getIdEquivalencePointSystem();
        this.name = equivalencePointSystem.getName();
    }

    public static List<EquivalencePointSystemView> listOf(List<EquivalencePointSystem> equivalencePointSystemList) {
        List<EquivalencePointSystemView> equivalencePointSystemViewList = new ArrayList<>();
        for (EquivalencePointSystem equivalencePointSystem : equivalencePointSystemList) {
            equivalencePointSystemViewList.add(new EquivalencePointSystemView(equivalencePointSystem));
        }
        return equivalencePointSystemViewList;
    }
}
