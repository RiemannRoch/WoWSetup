package com.riemannroch.wowsetup.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "TB_ITEM_EP")
@Data
public class ItemEquivalencePoints implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    ItemEquivalencePointsKey id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private ItemModel item;

    @ManyToOne
    @MapsId("epsId")
    @JoinColumn(name = "eps_id")
    private EquivalencePointSystemModel eps;

    private double equivalencePoints;

    public ItemEquivalencePoints(ItemModel item, EquivalencePointSystemModel eps) {
        this.id = new ItemEquivalencePointsKey(item.getIdItem(),eps.getIdEquivalencePointSystem());
        this.item = item;
        this.eps = eps;
        this.equivalencePoints = item.equivalencePoints(eps);
    }
}
